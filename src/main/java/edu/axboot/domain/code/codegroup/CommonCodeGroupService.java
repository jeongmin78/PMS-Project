package edu.axboot.domain.code.codegroup;

import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.ArrayUtils;
import com.querydsl.core.BooleanBuilder;
import edu.axboot.domain.BaseService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommonCodeGroupService extends BaseService<CommonCodeGroup, Long> {
    private CommonCodeGroupRepository commonCodeGroupRepository;

    @Inject
    public CommonCodeGroupService(CommonCodeGroupRepository commonCodeGroupRepository) {
        super(commonCodeGroupRepository);
        this.commonCodeGroupRepository = commonCodeGroupRepository;
    }

    public List<CommonCodeGroup> gets(RequestParams<CommonCodeGroup> requestParams) {
        return findAll();
    }

    public List<CommonCodeGroup> get(RequestParams requestParams) {
        String groupCd = requestParams.getString("groupCd","");
        String returnType = requestParams.getString("returnType", "hierarchy");
        boolean groupOpen = requestParams.getBoolean("groupOpen", true);
        List<Long> groupIds = (List<Long>) requestParams.getObject("groupIds");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(groupCd)) {
            builder.and(qCommonCodeGroup.groupCd.eq(groupCd));
        }
        List<CommonCodeGroup> groupList = select()
                .from(qCommonCodeGroup)
//                .rightJoin(qCommonCodeGroup.groupCd, qCommonCode)
//                .fetchJoin()
                .orderBy(qCommonCodeGroup.level.asc(), qCommonCodeGroup.sort.asc())
                .fetch();

        if (returnType.equals("hierarchy")) {
            List<CommonCodeGroup> hierarchyList = new ArrayList<>();
            List<CommonCodeGroup> filterList = new ArrayList<>();

            for (CommonCodeGroup group : groupList) {
                group.setOpen(groupOpen);

                if (groupIds != null) {
                    if (isNotEmpty(group.getGroupCd()) && !groupIds.contains(group.getGroupId())) {
                        continue;
                    }
                }

                CommonCodeGroup parent = getParent(hierarchyList, group);

                if (parent == null) {
                    hierarchyList.add(group);
                } else {
                    parent.addChildren(group);
                }
            }

            if (groupIds != null) {
                filterNoChildMenu(filterList, hierarchyList);
            } else {
                filterList.addAll(hierarchyList);
            }

            return filterList;
        }
        return groupList;
    }

    public CommonCodeGroup getParent(List<CommonCodeGroup> groups, CommonCodeGroup group) {
        CommonCodeGroup parent = groups.stream().filter(m -> m.getId().equals(group.getParentId())).findAny().orElse(null);

        if (parent == null) {
            for (CommonCodeGroup _group : groups) {
                parent = getParent(_group.getChildren(), group);

                if (parent != null)
                    break;
            }
        }

        return parent;
    }
    public void filterNoChildMenu(List<CommonCodeGroup> filterList, List<CommonCodeGroup> startList) {
        if (isNotEmpty(startList)) {
            for (CommonCodeGroup group : startList) {
                if (hasTerminalGroup(group)) {
                    CommonCodeGroup parent = getParent(filterList, group);

                    if (parent == null) {
                        filterList.add(group.clone());
                    } else {
                        parent.addChildren(group.clone());
                    }
                }

                if (isNotEmpty(group.getChildren())) {
                    filterNoChildMenu(filterList, group.getChildren());
                }
            }
        }
    }
    public boolean hasTerminalGroup(CommonCodeGroup group) {
        boolean hasTerminalGroup = false;

        if (isNotEmpty(group.getChildren())) {
            for (CommonCodeGroup _group : group.getChildren()) {
                hasTerminalGroup = hasTerminalGroup(_group);

                if (hasTerminalGroup) {
                    return true;
                }
            }
        }

        if (isNotEmpty(group.getGroupCd())) {
            hasTerminalGroup = true;
        }

        return hasTerminalGroup;
    }

    @Transactional
    public void updateGruop(Long id, CommonCodeGroup request) {
        CommonCodeGroup exist = findOne(id);
        exist.setGroupNm(request.getGroupNm());
        exist.setGroupCd(request.getGroupCd());
        save(exist);
    }

    public void processGruop(CommonCodeRequestVO codeRequestVO) {
        saveGroup(codeRequestVO.getList());
        deleteGroup(codeRequestVO.getDeletedList());
    }

    @Transactional
    public void saveGroup(List<CommonCodeGroup> groups) {
        if (ArrayUtils.isNotEmpty(groups)) {
            groups.forEach(m -> {
                if (isEmpty(m.getGroupCd())) {
                    m.setGroupCd("New Item");
                }
                if (m.getLevel() == 0) {
                    m.setParentId(null);
                }
                if (m.getUseYn() == null) {
                    m.setUseYn("Y");
                }

            });

            save(groups);
            groups.stream().filter(group-> isNotEmpty(group.getChildren())).forEach(group -> {
                group.getChildren().forEach(m->m.setParentId(group.getGroupId()));
                saveGroup(group.getChildren());
            });
        }

    }

    @Transactional
    private void deleteGroup(List<CommonCodeGroup> groups) {
        if (ArrayUtils.isNotEmpty(groups)) {
            delete(groups);
            groups.stream().filter(group -> isNotEmpty(group.getChildren())).forEach(group -> {
                deleteGroup(group.getChildren());
            });
        }
    }
}