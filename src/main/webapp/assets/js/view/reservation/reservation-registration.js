// function dateAddDel(sDate, nNum, type) {
//     var yy = parseInt(sDate.substr(0, 4), 10);
//     var mm = parseInt(sDate.substr(5, 2), 10);
//     var dd = parseInt(sDate.substr(8), 10);
    
//     if (type == "d") {
//         d = new Date(yy, mm - 1, dd + nNum);
//     }
//     else if (type == "m") {
//         d = new Date(yy, mm - 1, dd + (nNum * 31));
//     }
//     else if (type == "y") {
//         d = new Date(yy + nNum, mm - 1, dd);
//     }
 
//     yy = d.getFullYear();
//     mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
//     dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;
 
//     return '' + yy + '-' +  mm  + '-' + dd;
// }
// function dateDiff(_date1, _date2) {
//     var diffDate_1 = _date1 instanceof Date ? _date1 : new Date(_date1);
//     var diffDate_2 = _date2 instanceof Date ? _date2 : new Date(_date2);
 
//     diffDate_1 = new Date(diffDate_1.getFullYear(), diffDate_1.getMonth()+1, diffDate_1.getDate());
//     diffDate_2 = new Date(diffDate_2.getFullYear(), diffDate_2.getMonth()+1, diffDate_2.getDate());
 
//     var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
//     diff = Math.ceil(diff / (1000 * 3600 * 24));
 
//     return diff;
// }

var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: "GET",
            url: '/api/v1/chk/select',
            callback: function (res) {
                console.log(res);
                // caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                }
            }
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var item = caller.formView01.getData();
        var memos = [].concat(caller.gridView01.getData());
        memos = memos.concat(caller.gridView01.getData('deleted'));
        item.memoList = memos;
        console.log(item); //return false;
        axboot.ajax({
            type: "POST",
            url: '/api/v1/chk',
            data: JSON.stringify(item),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push("저장 되었습니다");
            }
        });
    },
    ITEM_CLICK: function (caller, act, data) {

    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow("selected");
    },
    MODAL_OPEN: function (caller, act, data) {
        if (!data) data = {};

        axboot.modal.open({
            width: 780,
            height: 450,
            iframe: {
                param: 'id=' + (data.id || ''),
                url: 'reservation-registration-content.jsp',
            },
            header: { title: '모달등록' },
            callback: function (data) {
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                }
                this.close();
            },
        });
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != "error") {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    }
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {

};


fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, "data-page-btn", {
            "search": function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN);
            },
            "save": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            "excel": function () {

            }
        });
    }
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document["searchView0"]);
        this.target.attr("onsubmit", "return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);");
        this.filter = $("#filter");
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val()
        }
    }
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
                { key: 'memoDtti', label: "작성일", width: '30%', align: 'center', editor: { type: 'text' } },
                { key: 'memoCn', label: "메모", width: '70%', align: 'center', editor: { type: 'text' } },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });
        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true}, 'last');
    },
});

/**
 * formView
 */
 fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        var today = new Date();
        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    },
    getData: function () {
        var _this = this;
        var data = _this.model.get();
        if($('.js-advnYn').is(":checked")==true) {
            data.advnYn = 'Y';
        } else data.advnYn = 'N';
        return $.extend({}, data);
    },
    setData: function (data) {
        data = $.extend({}, data);
        this.model.setModel(data);
    },
    validate: function () {
        var item = this.model.get();

        var rs = this.model.validate();
        if (rs.error) {
            axDialog.alert(LANG('ax.script.form.validate', rs.error[0].jquery.attr('title')), function () {
                rs.error[0].jquery.focus();
            });
            return false;
        }
        return true;
    },
    initEvent: function () {
        axboot.buttonClick(this, 'data-form-view-01-btn', {
            'form-clear': function () {
                ACTIONS.dispatch(ACTIONS.FORM_CLEAR);
            },
        });
    },
    calcDepDt: function(value){
        console.log('call calcDepDt...');
        if(!value) return;
        var arrDt = $('.js-arrDt').val();
        if(!arrDt) return;
        
        var depDt= moment(arrDt).add(value, 'day').format('yyyy-MM-DD');
        console.log('depDt', depDt);
        this.model.set('depDt', depDt);
    },
    calcNigth: function(depDt){
        console.log('call calcNigth...');
        if(!depDt) return;
        var arrDt = $('.js-arrDt').val();
        if(!arrDt) return;
        
        var night = moment(arrDt).diff(moment(depDt), 'days');
        console.log(arrDt, depDt);
        console.log('night', night);
        this.model.set('nightCnt',night);
    },
    initView: function () {
        var _this = this; // fnObj.formView01
        _this.target = $('.js-form');
        this.model = new ax5.ui.binder();
        this.model.setModel({}, this.target);
        this.initEvent();
        
        $('.js-nightCnt').on('change', function () {
            _this.calcDepDt($(this).val());
        });
        
        $('.js-depDt').on('change', function () {
            _this.calcNigth($(this).val());
        });
        
        $('.js-arrDt').on('change', function () {
            _this.calcNigth($(this).val());
        });
        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            modalsearch: function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN);
            },
        });
    },
    
});