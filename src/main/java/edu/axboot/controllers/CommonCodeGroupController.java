package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.domain.code.CommonCodeService;
import edu.axboot.domain.code.codegroup.CommonCodeGroup;
import edu.axboot.domain.code.codegroup.CommonCodeGroupService;
import edu.axboot.domain.code.codegroup.CommonCodeRequestVO;
import edu.axboot.domain.user.auth.menu.AuthGroupMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/commoncodegroup")
public class CommonCodeGroupController extends BaseController {

    @Inject
    private CommonCodeGroupService commonCodeGroupService;
    @Inject
    private CommonCodeService commonCodeService;


    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse groupList(RequestParams requestParams) {
        List<CommonCodeGroup> list = commonCodeGroupService.get(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody CommonCodeRequestVO codeRequestVO) {
        commonCodeGroupService.processGruop(codeRequestVO);
        return ok();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse update(@PathVariable Long id, @RequestBody CommonCodeGroup commonCodeGroup) {
        commonCodeGroupService.updateGruop(id, commonCodeGroup);
        return ok();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET}, produces = APPLICATION_JSON)
    public CommonCodeGroup update(@PathVariable Long id) {
        return commonCodeGroupService.findOne(id);
    }

    @RequestMapping(value = "/group", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<CommonCodeGroup> commonCodeGroupList) {
        commonCodeGroupService.saveGroup(commonCodeGroupList);
        return ok();
    }

//    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = APPLICATION_JSON)
//    public AuthGroupMenuVO authMapList(RequestParams requestParams) {
//        return authGroupMenuService.get(requestParams);
//    }
//
//    @RequestMapping(value = "/auth", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
//    public ApiResponse save(@RequestBody List<AuthGroupMenu> authGroupMenuList) {
//        authGroupMenuService.saveAuth(authGroupMenuList);
//        return ok();
//    }
}