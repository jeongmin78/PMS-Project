<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/information/guest-information.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>

        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="*">
                    <ax:tr>
                        <ax:td label='이름' width="300px">
                            <input type="text" class="form-control js-guestNm" />
                        </ax:td>
                        <ax:td label='전화번호' width="300px">
                            <input type="text" class="form-control js-guestTel" />
                        </ax:td>
                        <ax:td label='이메일' width="300px">
                            <input type="text" class="form-control js-email" />
                        </ax:td>
                    </ax:tr>
                    <!-- <ax:tr>
                        <ax:td label='투숙날짜' width="500px">
                            <button type="button" class="btn btn-today">오늘</button>
                            <button type="button" class="btn btn-yesterday">어제</button>
                            <button type="button" class="btn btn-3days">3일</button>
                            <button type="button" class="btn btn-7days">7일</button>
                            <button type="button" class="btn btn-1month">1개월</button>
                            <button type="button" class="btn btn-3month">3개월</button>
                            <button type="button" class="btn btn-6month">6개월</button>
                            <button type="button" class="btn btn-1year">1년</button>
                        </ax:td>
                    </ax:tr> -->
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="*" style="padding-right: 10px;">

                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>투숙객 목록 </h2>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
            </ax:split-panel>
            <ax:splitter></ax:splitter>
            <ax:split-panel width="*" style="padding-left: 10px; " >
                    <div class="ax-button-group" role="panel-header">
                        <div class="left">
                            <h2><i class="cqc-news"></i> 투숙객 정보 </h2>
                        </div>
                    </div>

                    <form name="form" class="js-form" onsubmit="return false;">
                        <div data-ax-tbl class="ax-form-tbl">

                            <div data-ax-tr>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:100px">이름</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control">
                                    </div>
                                </div>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:100px">영문</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:100px">연락처</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" name="guestTel" data-ax-path="guestTel" class="form-control"  data-ax5formatter="phone">
                                    </div>
                                </div>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:100px">이메일</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" name="email" data-ax-path="email" class="form-control" placeholder="x@x.xx">
                                    </div>
                                </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:40%">
                                    <div data-ax-td-label style="width:100px">언어</div>
                                    <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_LANG" dataPath="langCd" clazz="js-langCd" />
                                    </div>
                                </div>
                                <div data-ax-td style="width:60%">
                                    <div data-ax-td-label style="width:100px">생년월일</div>
                                    <div data-ax-td-wrap>
                                        <input type="date" name="brth" data-ax-path="brth" class="form-control">
                                    </div>
                                    <div data-ax-td-wrap>
                                        <input type="radio" id="male" name="gender" data-ax-Path="gender" value="남">
                                        <label for="male">남</label>
                                        <input type="radio" id="female" name="gender" data-ax-Path="gender" value="여">
                                        <label for="female">여</label>
                                    </div>
                                </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:80%">
                                    <div data-ax-td-label style="width:100px">비고</div>
                                    <div data-ax-td-wrap>
                                        <textarea name="rmk" data-ax-path="rmk" rows="5" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div>
                        <div class="" >
                            <!-- 목록 -->
                            <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                <div class="left">
                                    <h2><i class="cqc-list"></i> 투숙 이력 </h2>
                                </div>
                            </div>
                            <div data-ax5grid="grid-view-02" style="height: 300px;"></div>
                        </div>
                    </div>
            </ax:split-panel>

        </ax:split-layout>
    </jsp:body>
</ax:layout>