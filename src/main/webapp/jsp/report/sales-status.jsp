<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/report/sales-status.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons>
            <button type="button" class="btn btn-info" data-page-btn="search"><i class="cqc-magnifier"></i> 검색 </button>
            <button type="button" class="btn btn-info" data-page-btn="excel"><i class="cqc-circle-with-excel"></i> 엑셀 다운로드</button>
        </ax:page-buttons>

        <div role="page-header">
            <form name="searchView0" id="searchView0" method="post" onsubmit="return false;">
                <div data-ax-tbl class="ax-search-tbl">
                    <div data-ax-tr>
                        <di data-ax-td style="width:100%">
                            <div data-ax-td-label>조회날짜</div>
                            <div data-ax-td-wrap>
                                <div class="form-inline">
                                    <button type="button" class="btn btn-default js-today" data-search-view-btn="today">오늘</button>
                                    <button type="button" class="btn btn-default js-yesterday" data-search-view-btn="yesterday">어제</button>
                                    <button type="button" class="btn btn-default js-threedays" data-search-view-btn="threedays">3일</button>
                                    <button type="button" class="btn btn-default js-sevendays" data-search-view-btn="sevendays">7일</button>
                                    <button type="button" class="btn btn-default js-onemonth" data-search-view-btn="onemonth">1개월</button>
                                    <button type="button" class="btn btn-default js-sixmonth" data-search-view-btn="sixmonth">6개월</button>
                                    <button type="button" class="btn btn-default js-oneyear" data-search-view-btn="oneyear">1년</button>
                                    <div class="input-group" data-ax5picker="date">
                                        <input type="text" class="form-control js-arrDt" placeholder="yyyy-mm-dd">
                                        <span class="input-group-addon">~</span>
                                        <input type="text" class="form-control js-arrDt-end" placeholder="yyyy-mm-dd">
                                        <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">

                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            보고서 </h2>
                    </div>

                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>