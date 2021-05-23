var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data);
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/chk',
            data: paramObj,
            callback: function (res) {
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    MODAL_OPEN: function (caller, act, data) {
        if (!data) data = {};
        axboot.modal.open({
            width: 900,
            height: 700,
            iframe: {
                param: { id: data.id || '' },
                url: 'reservation-current-state-content.jsp',
            },
            header: { title: '예약 조회' },
            callback: function (data) {
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                }
                this.close();
            },
        });
    },
    STTUS_CHANGE: function (caller, act, data) {
        var item = caller.gridView01.getData('selected');
        var sttusCd = $('.js-sttusCd').val();
        console.log(item[0].id);
        var ids = [];
        $.each(item, function (i) {
            ids.push(item[i].id);
        });
        console.log(ids);
        axboot.ajax({
            type: 'POST',
            url: '/api/v1/chk/sttus' + '?ids=' + ids.join(',') + '&sttusCd=' + sttusCd,
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });
    },
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
    },
    SEARCH_CLEAR: function (caller, act, data) {
        caller.searchView.clear();
    },
    EXCEL_DOWN: function (caller, act, data) {
        // var frm = $('[data-ax5grid="grid-view-01"]').get(0);
        var frm = $('.js-form').get(0);
        console.log(frm);
        frm.action = '/api/v1/chk/exceldown';
        frm.enctype = 'application/x-www-form-urlencoded';
        frm.submit();
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            fn1: function () {
                ACTIONS.dispatch(ACTIONS.SEARCH_CLEAR);
            },
            excel: function () {
                ACTIONS.dispatch(ACTIONS.EXCEL_DOWN);
            },
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        this.target.attr('onsubmit', 'return false;');

        this.target.find('[data-ax5picker="date"]').ax5picker({
            direction: 'auto',
            content: {
                type: 'date',
            },
        });

        this.filter = $('#filter');
        this.rsvNum = $('.js-rsvNum');

        this.rsvDt = $('.js-rsvDt-start');
        this.rsvDtEnd = $('.js-rsvDt-end');

        this.roomTypCd = $('.js-roomTypCd').on('change', function () {
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });
        this.arrDt = $('.js-arrDt-start');
        this.arrDtEnd = $('.js-arrDt-end');

        this.depDt = $('.js-depDt-start');
        this.depDtEnd = $('.js-depDt-end');
    },
    getData: function () {
        var select_obj = '';
        $("input[name='sttus']:checked").each(function (index) {
            if (index != 0) {
                select_obj += ',';
            }
            select_obj += $(this).val();
        });
        console.log(select_obj);
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 50,
            filter: this.filter.val(),
            rsvNum: this.rsvNum.val(),
            rsvDt: this.rsvDt.val(),
            rsvDtEnd: this.rsvDtEnd.val(),
            roomTypCd: this.roomTypCd.val(),
            arrDt: this.arrDt.val(),
            arrDtEnd: this.arrDtEnd.val(),
            depDt: this.depDt.val(),
            depDtEnd: this.depDtEnd.val(),
            sttusCd: select_obj,
        };
    },
    clear: function () {
        $(document['searchView0']).each(function () {
            this.reset();
        });
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'rsvNum', label: '예약번호', width: 150, align: 'center' },
                { key: 'rsvDt', label: '예약일', width: 150, align: 'center' },
                { key: 'guestNm', label: '투숙객', width: 100, align: 'center' },
                {
                    key: 'roomTypCd',
                    label: '객실타입',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_ROOM_TYPE'].map[this.value];
                    },
                },
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center' },
                { key: 'arrDt', label: '도착일', width: 150, align: 'center' },
                { key: 'depDt', label: '출발일', width: 150, align: 'center' },
                {
                    key: 'srcCd',
                    label: '예약경로',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_RESERVATION_ROUTE'].map[this.value];
                    },
                },
                {
                    key: 'saleTypCd',
                    label: '판매유형',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_SALE_TYPE'].map[this.value];
                    },
                },
                {
                    key: 'sttusCd',
                    label: '상태',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_STAY_STATUS'].map[this.value];
                    },
                },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
                onDBLClick: function () {
                    console.log(this.item.id);
                    ACTIONS.dispatch(ACTIONS.MODAL_OPEN, this.item);
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            change: function () {
                axDialog.confirm({ msg: '선택한 항목의 상태를 변경 하시겠습니까?' }, function () {
                    if (this.key == 'ok') {
                        ACTIONS.dispatch(ACTIONS.STTUS_CHANGE);
                    }
                });
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                delete this.deleted;
                return this.key;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true }, 'last');
    },
});
