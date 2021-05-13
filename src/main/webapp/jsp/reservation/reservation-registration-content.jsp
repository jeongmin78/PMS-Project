<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="modal">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/information/guest-information.js' />"></script>
    </jsp:attribute>
    <jsp:body>
        <ax:split-panel width="*" style="">
            <!-- 목록 -->
            <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                <div class="left">
                    <h2><i class="cqc-list"></i>투숙객 목록 </h2>
                </div>
            </div>
            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 150px;"></div>
        </ax:split-panel>
        <ax:split-panel width="*" style="padding-top: 5px;">
            <div data-fit-height-aside="form-view-01">
                <form name="form" class="js-form">
                    <ax:tbl clazz="ax-form-tbl" minWidth="100px">
                        <ax:tr labelWidth="100px">
                            <ax:td label="이름" width="40%">
                                <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control"/>
                            </ax:td>
                            <ax:td label="영문" width="40%">
                                <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control"/>
                            </ax:td>
                        </ax:tr>

                        <ax:tr labelWidth="100px">
                            <ax:td label="연락처" width="40%">
                                <input type="text" name="guestTel" data-ax-path="guestTel" class="form-control"/>
                            </ax:td>
                            <ax:td label="이메일" width="40%">
                                <input type="text" name="email" data-ax-path="email" class="form-control"  />
                            </ax:td>
                        </ax:tr>

                        <ax:tr labelWidth="100px">
                            <ax:td label="언어" width="40%">
                                <input type="text" name="langCd" data-ax-path="langCd" title="사업자번호" class="form-control"/>
                            </ax:td>
                            <ax:td label="생년월일" width="40%">
                                <input type="text" name="brth" data-ax-path="brth" class="form-control" />
                            </ax:td>
                        </ax:tr>
                        
                        <ax:tr labelWidth="100px">
                            <ax:td label="비고" width="100%">
                                <textarea name="rmk" data-ax-path="rmk" rows="5" class="form-control"></textarea>
                            </ax:td>
                        </ax:tr>

                    </ax:tbl>
                </form>
            </div>

        </ax:split-panel>
    </jsp:body>
</ax:layout>