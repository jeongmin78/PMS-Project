var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = caller.searchView.getData();
        if (data) paramObj.arrDt = data;
        console.log(paramObj);
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/chk/total',
            data: paramObj,
            callback: function (res) {
                console.log(res);
                caller.gridView01.clear();
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
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
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
            excel: function () {},
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

        this.model = new ax5.ui.binder();
        this.model.setModel({}, this.target);

        this.arrDt = $('.js-arrDt');
        this.arrDtEnd = $('.js-arrDt-end');

        console.log(this);

        $('.js-today').onClick = function () {
            this.model.set('arrDt', moment().format('yyyy-MM-DD'));
            this.model.set('arrDtEnd', '');
        };
        axboot.buttonClick(this, 'data-search-view-btn', {
            today: function () {
                console.log(this.model);
                this.model.set('arrDt', moment().format('yyyy-MM-DD'));
                this.model.set('arrDtEnd', '');
                // ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().format('yyyy-MM-DD'));
            },
            yesterday: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().add(-1, 'days').format('yyyy-MM-DD'));
            },
            threedays: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().add(-3, 'days').format('yyyy-MM-DD'));
            },
            sevendays: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().add(-7, 'days').format('yyyy-MM-DD'));
            },
            onemonth: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().add(-1, 'months').format('yyyy-MM-DD'));
            },
            sixmonth: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().add(-6, 'months').format('yyyy-MM-DD'));
            },
            oneyear: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, moment().add(-1, 'years').format('yyyy-MM-DD'));
            },
        });
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            arrDt: this.arrDt.val(),
            arrDtEnd: this.arrDtEnd.val(),
        };
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
            frozenRowIndex: 1,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'arrDt', label: '날짜', width: 150, align: 'center' },
                { key: 'roomCount', label: '투숙건수', collector: 'count', width: 150, align: 'center' },
                { key: 'salePrc', label: '판매금액', width: 100, align: 'center', formatter: 'money' },
                { key: 'svcPrc', label: '서비스금액', width: 100, align: 'center', formatter: 'money' },
                {
                    key: 'salePrc',
                    label: '합계',
                    width: 150,
                    align: 'center',
                    formatter: function () {
                        var value = 0;
                        value = this.item.salePrc + this.item.svcPrc;
                        return ax5.util.number(value, { money: 1 });
                    },
                },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
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
