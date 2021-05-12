<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/reservation/reservation-registration.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>


        <div role="page-header">
            <form name="form" class="js-form" onsubmit="return false;">
                <div data-ax-tbl class="ax-form-tbl">

                    <div data-ax-tr>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">도착일</div>
                            <div data-ax-td-wrap>
                                <input type="date" data-ax-path="arrDt" class="form-control js-arrDt">
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">숙박수</div>
                            <div data-ax-td-wrap>
                                <input type="text" data-ax-path="nightCnt" class="form-control js-nightCnt">
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">출발일</div>
                            <div data-ax-td-wrap>
                                <input type="date" data-ax-path="depDt" class="form-control js-depDt">
                            </div>
                        </div>
                    </div>
                    <div data-ax-tr>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">객실타입</div>
                            <div data-ax-td-wrap>
                                <ax:common-code groupCd="PMS_ROOM_TYPE" clazz="js-roomTypCd" />
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">성인수</div>
                            <div data-ax-td-wrap>
                                <input type="text" data-ax-path="adultCnt" class="form-control js-adultCnt">
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">아동수</div>
                            <div data-ax-td-wrap>
                                <input type="text" data-ax-path="chldCnt" class="form-control js-chldCnt">
                            </div>
                        </div>
                    </div>

                    <div data-ax-tr>
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;">투숙객</div>
                            <div data-ax-td-wrap>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이름</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="guestNm" class="form-control js-guestNm" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">영문</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="guestNmEng" class="form-control js-guestNmEng" />
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">연락처</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="guestTel" class="form-control js-guestTel" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이메일</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="email" class="form-control js-email" />
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">언어</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_LANG" clazz="js-lang" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">생년월일</div>
                                        <div data-ax-td-wrap>
                                            <input type="date" class="form-control" />
                                        </div>
                                    </div>                                                                      
                                </div>                                
                           
                            </div>
                        </div>
                    </div>
                    
                    <div data-ax-tr>
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;">판매/결제</div>
                            <div data-ax-td-wrap>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">판매유형</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_SALE_TYPE" clazz="js-saleType" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">예약경로</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_RESERVATION_ROUTE" clazz="js-reservationRoute" />
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제방법</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_PAY_METHOD" clazz="js-payMethod" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">선수금 여부</div>
                                        <div data-ax-td-wrap>
                                            <input type="checkbox" class="form-control" />
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제금액</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" class="form-control" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">서비스금액</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" class="form-control" />
                                        </div>
                                    </div>                                                                      
                                </div>                                
                           
                            </div>
                        </div>
                    </div>
                    <div data-ax-tr>
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;">투숙메모</div>
                            <div data-ax-td-wrap>
                                <textarea name="remark" data-ax-path="remark" rows="5" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>
</div>
    </jsp:body>
</ax:layout>