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
        </ax:page-buttons>


        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='검색어' width="300px">
                            <input type="text" class="form-control js-filter" />
                        </ax:td>
                        <ax:td label='예약번호' width="300px">
                            <input type="text" class="form-control js-rsvNum" />
                        </ax:td>
                        <ax:td label='예약일' width="300px">
                            <input type="date" class="form-control js-rsvDt" />
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label='객실타입' width="300px">
                            <select class="form-control js-roomTypCd">
                                <option value="">전체</option>
                                <option value="SB">SB</option>
                                <option value="DB">DB</option>
                                <option value="DT">DT</option>
                            </select>
                        </ax:td>
                        <ax:td label='도착일' width="300px">
                            <input type="date" class="form-control js-arrDt" />
                        </ax:td>
                        <ax:td label='출발일' width="300px">
                            <input type="date" class="form-control js-depDt" />
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label='상태' width="100%">
                            <form class="js-SttusCd">
                                <label><input type='checkbox' name='status' value='all' />  전체  </label>
                                <label><input type='checkbox' name='status' value='RSV_01' />  예약  </label>
                                <label><input type='checkbox' name='status' value='RSV_02' />  예약대기  </label>
                                <label><input type='checkbox' name='status' value='RSV_03' />  예약확정  </label>
                                <label><input type='checkbox' name='status' value='RSV_04' />  예약취소  </label>
                                <label><input type='checkbox' name='status' value='RSV_05' />  노쇼  </label>
                                <label><input type='checkbox' name='status' value='CHK_01' />  체크인  </label>
                                <label><input type='checkbox' name='status' value='CHK_02' />  체크아웃  </label>
                                <label><input type='checkbox' name='status' value='CHK_03' />  체크인취소  </label>
                            </form>
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">

                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            프로그램 목록 </h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i class="cqc-circle-with-plus"></i> 삭제</button>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>