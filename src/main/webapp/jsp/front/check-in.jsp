<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/front/check-in.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons>
            <button type="button" class="btn btn-info" data-page-btn="search"><i class="cqc-magnifier"></i> 검색 </button>
            <button type="button" class="btn btn-info" data-page-btn="refresh"> 검색선택초기화 </button>
            <button type="button" class="btn btn-info" data-page-btn="excel"><i class="cqc-circle-with-excel"></i> 엑셀 다운로드</button>
        </ax:page-buttons>
        
        <div role="page-header">
            <form name="searchView0" id="searchView0">
                <div data-ax-tbl class="ax-search-tbl">
                <div data-ax-tr>
                    <div data-ax-td style="width:25%">
                        <div data-ax-td-label>검색어</div>
                        <div data-ax-td-wrap>
                            <input type="text" class="form-control js-filter">
                        </div>
                    </div>
                    <div data-ax-td style="width:30%">
                        <div data-ax-td-label>예약번호</div>
                        <div data-ax-td-wrap>
                            <input type="text" class="form-control js-rsvNum">
                        </div>
                    </div>
                    <div data-ax-td style="width:30%">
                        <div data-ax-td-label>도착일</div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date">
                                    <input type="text" data-ax-path="arrDt" class="form-control js-depDt" placeholder="yyyy-mm-dd">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
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
                            도착 목록 </h2>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>