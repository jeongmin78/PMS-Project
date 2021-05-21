<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/reservation/reservation-current-state.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons>
            <button type="button" class="btn btn-info" data-page-btn="search"><i class="cqc-magnifier"></i> 검색 </button>
            <button type="button" class="btn btn-info" data-page-btn="fn1"> 검색선택초기화 </button>
            <button type="button" class="btn btn-info" data-page-btn="excel"><i class="cqc-circle-with-excel"></i> 엑셀 다운로드</button>
        </ax:page-buttons>

        <div role="page-header">
            <form name="searchView0" id="searchView0" method="post" onsubmit="return false;" style="">
                <div data-ax-tbl="" id="" class="ax-search-tbl" style="">
                <div data-ax-tr="" id="" class="" style="">
                <div data-ax-td="" id="" class="" style=";width:300px">
                    <div data-ax-td-label="" class="" style="">검색어</div>
                    <div data-ax-td-wrap="">
                        <input type="text" class="form-control js-filter">
                    </div>
                </div>
                <div data-ax-td="" id="" class="" style=";width:400px">
                    <div data-ax-td-label="" class="" style="">예약번호</div>
                    <div data-ax-td-wrap="">
                        <input type="text" class="form-control js-rsvNum">
                    </div>
                </div>
                <div data-ax-td="" id="" class="" style=";width:400px">
                    <div data-ax-td-label="" class="" style="">예약일</div>
                    <div data-ax-td-wrap="">
                        <input type="date" class="form-control js-rsvDt-start" width="140px">
                    </div>
                    <div data-ax-td-wrap="">
                        <input type="date" class="form-control js-rsvDt-end" width="140px">
                    </div>
                </div>
                </div>
                <div data-ax-tr="" id="" class="" style="">
                <div data-ax-td="" id="" class="" style=";width:300px">
                    <div data-ax-td-label="" class="" style="">객실타입</div>
                    <div data-ax-td-wrap="">
                        <ax:common-code groupCd="PMS_ROOM_TYPE" dataPath="roomTypCd" clazz="js-roomTypCd" emptyText="전체" />
                    </div>
                </div>
                <div data-ax-td="" id="" class="" style=";width:400px">
                    <div data-ax-td-label="" class="" style="">도착일</div>
                    <div data-ax-td-wrap="">
                        <input type="date" class="form-control js-arrDt-start">
                    </div>
                    <div data-ax-td-wrap="">
                        <input type="date" class="form-control js-arrDt-end">
                    </div>
                </div>
                <div data-ax-td="" id="" class="" style=";width:400px">
                    <div data-ax-td-label="" class="" style="">출발일</div>
                    <div data-ax-td-wrap="">
                        <input type="date" class="form-control js-depDt-start">
                    </div>
                    <div data-ax-td-wrap="">
                        <input type="date" class="form-control js-depDt-end">
                    </div>
                </div>
                </div>
                <div data-ax-tr="" id="" class="" style="">
                <div data-ax-td="" id="" class="" style=";width:100%">
                    <div data-ax-td-label="" class="" style="">상태</div>
                    <div data-ax-td-wrap="">
                        <label><input type="checkbox" name="sttus" value="">  전체  </label>
                        <label><input type="checkbox" name="sttus" value="RSV_01">  예약  </label>
                        <label><input type="checkbox" name="sttus" value="RSV_02">  예약대기  </label>
                        <label><input type="checkbox" name="sttus" value="RSV_03">  예약확정  </label>
                        <label><input type="checkbox" name="sttus" value="RSV_04">  예약취소  </label>
                        <label><input type="checkbox" name="sttus" value="RSV_05">  노쇼  </label>
                        <label><input type="checkbox" name="sttus" value="CHK_01">  체크인  </label>
                        <label><input type="checkbox" name="sttus" value="CHK_02">  체크아웃  </label>
                        <label><input type="checkbox" name="sttus" value="CHK_03">  체크인취소  </label>
                    </div>
                </div>
                </div>
                </div>
            </form>
                <div class="H10"></div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <form name="excelForm" class="js-form" method="post">
                    <div class="ax-button-group">
                        <div class="left">
                            <h2><i class="cqc-list"></i>
                                프로그램 목록 </h2>
                        </div>
                        <div class="right">
                            <div class="form-inline">
                                <ax:common-code groupCd="PMS_STAY_STATUS" dataPath="sttusCd" clazz="js-sttusCd"/>
                                <button type="button" class="btn btn-default" data-grid-view-01-btn="change">상태변경</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
            </div>
        </div>
    </jsp:body>
</ax:layout>