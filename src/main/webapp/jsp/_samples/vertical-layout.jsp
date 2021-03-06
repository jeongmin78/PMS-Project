<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${pageRemark}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="js">
    <script src="/assets/plugins/prettify/prettify.js"></script>
    <script src="/assets/plugins/prettify/lang-css.js"></script>
    </jsp:attribute>
    <jsp:attribute name="css">
        <link rel="stylesheet" type="text/css" href="/assets/plugins/prettify/skins/github.css"/>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/_samples/page-structure.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>

        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='ax.admin.sample.search.condition1' width="300px">
                            <input type="text" class="form-control" />
                        </ax:td>
                        <ax:td label='ax.admin.sample.search.condition2' width="300px">
                            <input type="text" class="form-control" />
                        </ax:td>
                        <ax:td label='ax.admin.sample.search.condition3' width="300px">
                            <input type="text" class="form-control" />
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>


        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="*" style="padding-right: 10px;">

                <div class="ax-button-group" data-fit-height-aside="left-view-01">
                    <div class="left">
                        <h2>
                            <i class="cqc-list"></i>
                            <ax:lang id="ax.admin.sample.layout.leftpanel"/> </h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-left-view-01-btn="add"><i class="cqc-circle-with-plus"></i> <ax:lang id="ax.admin.add"/></button>
                    </div>
                </div>

                <div data-fit-height-content="left-view-01" style="height: 300px;border: 1px solid #D8D8D8;background: #fff;">

<pre>
// ax:split-panel ?????? ???????????? ???????????? ????????? ???????????? ?????? ???????????? ?????????.
&lt;div data-fit-height-aside="left-view-01">&lt;/div>
&lt;div data-fit-height-content="left-view-01">&lt;/div>
&lt;div data-fit-height-aside="left-view-01">&lt;/div>
// [data-fit-height-aside] [data-dit-height-content] ??? ????????? ?????? ?????????????????? ??????????????? ???????????????
// ?????????????????? ?????? ?????? ??????????????? ????????????. aside????????? ???????????? ??? ???????????? content???????????? ???????????? style.height??? ???????????? ???????????????.
// data-fit-height ????????? ax5layout ?????? ?????? ????????? ???????????? ?????? ?????? ?????? ????????? ???????????? axboot.init?????? ????????? ?????? ????????????.
</pre>

                </div>

                <div class="ax-button-group ax-button-group-bottom" data-fit-height-aside="left-view-01">
                    <div class="left">
                        <button type="button" class="btn btn-default" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN0</button>
                        <button type="button" class="btn btn-primary" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN1</button>
                        <button type="button" class="btn btn-info" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN2</button>
                        <button type="button" class="btn btn-success" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN3</button>
                        <button type="button" class="btn btn-warning" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN3</button>
                        <button type="button" class="btn btn-danger" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN3</button>
                    </div>
                </div>

            </ax:split-panel>
            <ax:splitter></ax:splitter>
            <ax:split-panel width="*" style="padding-left: 10px;">

                <div class="ax-button-group" data-fit-height-aside="right-view-01">
                    <div class="left">
                        <h2>
                            <i class="cqc-list"></i>
                            <ax:lang id="ax.admin.sample.layout.rightpanel"/> </h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-right-view-01-btn="add"><i class="cqc-circle-with-plus"></i> <ax:lang id="ax.admin.add"/></button>
                    </div>
                </div>

                <div data-fit-height-content="right-view-01" style="height: 300px;border: 1px solid #D8D8D8;background: #fff;">

<pre>
&lt;div data-fit-height-aside="right-view-01">&lt;/div>
&lt;div data-fit-height-content="right-view-01">&lt;/div>
&lt;div data-fit-height-aside="right-view-01">&lt;/div>
</pre>

                </div>

                <div class="ax-button-group ax-button-group-bottom" data-fit-height-aside="right-view-01">
                    <div class="right">
                        <button type="button" class="btn btn-default" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN0</button>
                        <button type="button" class="btn btn-primary" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN1</button>
                        <button type="button" class="btn btn-info" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN2</button>
                        <button type="button" class="btn btn-success" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN3</button>
                        <button type="button" class="btn btn-warning" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN3</button>
                        <button type="button" class="btn btn-danger" data-left-view-01-btn="add"><ax:lang id="ax.admin.button"/> FN3</button>
                    </div>
                </div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>