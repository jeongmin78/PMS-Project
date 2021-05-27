<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="system-config-menu-version" value="1.0.0"/>
<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${pageRemark}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" />
        <ax:script-lang key="ax.admin" var="COL" />
        <script type="text/javascript" src="<c:url value='/assets/js/view/common-code/common-code.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons>
            <button type="button" class="btn btn-info" data-page-btn="save"><i class="cqc-save"></i> 저장</button>
        </ax:page-buttons>

        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="300" style="padding-right: 10px;">

                <div class="ax-button-group" data-fit-height-aside="tree-view-01">
                    <div class="left">
                        <h2>
                            <i class="cqc-list"></i>
                            <ax:lang id="ax.admin.menu.title"/> </h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-tree-view-01-btn="add"><i class="cqc-circle-with-plus"></i> <ax:lang id="ax.admin.add"/></button>
                    </div>
                </div>

                <div data-z-tree="tree-view-01" data-fit-height-content="tree-view-01" style="height: 300px;" class="ztree"></div>

            </ax:split-panel>
            <ax:splitter></ax:splitter>
            <ax:split-panel width="*" style="padding-left: 10px;" id="split-panel-form">

                <div data-fit-height-aside="form-view-01">
                    <div class="ax-button-group">
                        <div class="left">
                            <h2>
                                <i class="cqc-news"></i>
                                코드 설정 </h2>
                        </div>
                        <div class="right">
                        </div>
                    </div>

                    <form name="formView01" class="js-form">
                        <div data-ax-tbl class="ax-form-tbl">
                            <div data-ax-tr>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:150px;">분류코드</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" data-ax-path="groupCd" class="form-control js-groupCd" readonly="readonly"/>
                                    </div>
                                </div>
                                <div data-ax-td style="width:60%">
                                    <div data-ax-td-label style="width:150px;">분류명</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" data-ax-path="groupNm" class="form-control js-groupNm"/>
                                    </div>
                                </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:150px;">사용여부</div>
                                    <div data-ax-td-wrap>
                                        <ax:common-code groupCd="USE_YN" dataPath="useYn" clazz="js-useYn" />
                                    </div>
                                </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%">
                                    <div data-ax-td-label style="width:150px;">코드설명</div>
                                    <div data-ax-td-wrap>
                                        <textarea name="rmk" data-ax-path="rmk" rows="2" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 이거 지우면 에러남 -->
                        
                    </form>

                    <div class="H20"></div>
                    <!-- 목록 -->
                    <div class="ax-button-group">
                        <div class="left">
                            <h2><i class="cqc-list"></i>
                                코드 목록</h2>
                        </div>
                        <div class="right">

                        </div>
                    </div>
                </div>

                <div data-ax5grid="grid-view-01" data-fit-height-content="form-view-01" style="height: 300px;"></div>


            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>