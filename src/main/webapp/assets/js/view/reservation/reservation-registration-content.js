var modalParams = modalParams || {};
var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        if (!modalParams) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest',
            data: modalParams,
            callback: function (res) {
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
    },
    // PAGE_SAVE: function (caller, act, data) {
    //     var saveList = [].concat(caller.gridView01.getData('modified'));
    //     saveList = saveList.concat(caller.gridView01.getData('deleted'));

    //     axboot.ajax({
    //         type: 'PUT',
    //         url: '/api/v1/guest/',
    //         data: JSON.stringify(saveList),
    //         callback: function (res) {
    //             ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
    //             axToast.push('저장 되었습니다');
    //         },
    //     });
    // },
    ITEM_CLICK: function (caller, act, data) {
        // var id = (data || {}).id;
        // if (!id) {
        //     return false;
        // }
        // axboot.ajax({
        //     type: 'GET',
        //     url: '/api/v1/guest/' + id,
        //     callback: function (res) {
        //         caller.formView01.setData(res);
        //     },
        // });
        caller.formView01.setData(data || {});
    },
    // ITEM_SELECT: function (caller, act, data) {
    //     var item = caller.formView01.getData();
    //     axboot.ajax({
    //         type: 'GET',
    //         url: '/api/v1/guest/' + item.id,
    //         callback: function (res) {
    //             if (parent && parent.axboot && parent.axboot.modal) {
    //                 parent.axboot.modal.callback({ dirty: true, res });
    //             }
    //         },
    //     });
    // },
    PAGE_CLOSE: function (caller, act, data) {
        var modal = fnObj.getModal();
        if (modal) modal.close();
        if (opener) window.close();
    },
    ITEM_SELECT: function (caller, act, data) {
        // var item = caller.formView01.getData();
        // axboot.ajax({
        //     type: 'GET',
        //     url: '/api/v1/guest/' + item.id,
        //     callback: function (res) {
        //         var modal = fnObj.getModal();
        //         if (modal) modal.callback(res);
        //         if (opener) window.close();
        //     },
        // });
        if (!data) {
            var list = caller.gridView01.getData('selected');
            if (list.length > 0) data = list[0];
        }
        if (data) {
            var modal = fnObj.getModal();
            if (modal) modal.callback(data);
            if (opener) window.close();
        } else {
            alert('aa');
        }
    },
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
fnObj.getModal = function () {
    var modalView;
    console.log(modalParams.modalView);
    if (parent && modalParams.modalView && (modalView = parent[axboot.def.pageFunctionName][modalParams.modalView])) {
        return modalView;
    } else if (opener && modalParams.modalView && (modalView = opener[axboot.def.pageFunctionName][modalParams.modalView])) {
        return modalView;
    } else if (parent && parent.axboot && parent.axboot.modal) {
        return parent.axboot.modal;
    }
};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.gridView01.initView();
    this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_SELECT);
            },
            fn1: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
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
        this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
        };
    },
});

fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'guestNm', label: '이름', width: 160, align: 'center', editor: 'text' },
                { key: 'guestTel', label: '연락처', width: 200, align: 'center', editor: 'text' },
                { key: 'email', label: '이메일', width: 100, align: 'center', editor: 'text' },
                { key: 'gender', label: '성별', width: 100, align: 'center', editor: 'text' },
                { key: 'brth', label: '생년월일', width: 100, align: 'center', editor: 'text' },
                { key: 'langCd', label: '언어', width: 100, align: 'center', editor: 'text' },
                { key: 'rmk', label: '비고', width: 100, align: 'center', editor: 'text' },
            ],
            columnsDefs: [{ targets: [0], visible: false }],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                    ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
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
/**
 * formView
 */
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return {};
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        return $.extend({}, data);
    },
    setData: function (data) {
        console.log(data);

        data = $.extend({}, data);
        console.log(data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
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
        // required 이외 벨리데이션 정의
        var pattern;
        if (item.email) {
            pattern = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.(?:[A-Za-z0-9]{2,}?)$/i;
            if (!pattern.test(item.email)) {
                axDialog.alert('이메일 형식을 확인하세요.', function () {
                    $('[data-ax-path="email"]').focus();
                });
                return false;
            }
        }

        if (item.guestTel && !(pattern = /^([0-9]{3})\-?([0-9]{4})\-?([0-9]{4})$/).test(item.guestTel)) {
            axDialog.alert('전화번호 형식을 확인하세요.'),
                function () {
                    $('[data-ax-path="guestTel"]').focus();
                };
            return false;
        }
        return true;
    },

    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        _this.model = new ax5.ui.binder();
        _this.model.setModel({}, _this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
    },
});
