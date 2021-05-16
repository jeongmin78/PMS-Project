var modalParams = modalParams || {};
var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    MODAL_CLOSE: function (caller, act, data) {
        parent.axboot.modal.close();
    },
    PAGE_SEARCH: function (caller, act, data) {
        if (!modalParams.id) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest/' + modalParams.id,
            callback: function (res) {
                caller.formView01.setData(res);
            },
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            if (!item.id) item.__created__ = true;
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/guest' + modalParams.id,
                data: JSON.stringify(item),
                callback: function (res) {
                    if (parent && parent.axboot && parent.axboot.modal) {
                        parent.axboot.modal.callback({ dirty: true });
                    }
                    // axDialog.alert('저장 되었습니다', function () {
                    //     if (parent && parent.axboot && parent.axboot.modal) {
                    //         parent.axboot.modal.callback({ dirty: true });
                    //     }
                    // });
                },
            });
        }
    },
    PAGE_DELETE: function (caller, act, data) {
        if (!modalParams.id) return false;
        axboot.ajax({
            type: 'DELETE',
            url: '/api/v1/_education/yesjmgridform/' + modalParams.id,
            callback: function (res) {
                axDialog.alert('삭제 되었습니다', function () {
                    if (parent && parent.axboot && parent.axboot.modal) {
                        parent.axboot.modal.callback({ dirty: true });
                    }
                });
            },
        });
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm({ msg: LANG('ax.script.form.clearconfirm') }, function () {
            if (this.key == 'ok') {
                caller.formView01.clear();
                $('[data-ax-path="companyNm"]').focus();
            }
        });
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
    getData: function () {
        var data = this.model.get(); // 모델의 값을 포멧팅 전 값으로 치환.
        return $.extend({}, data);
    },
    setData: function (data) {
        data = $.extend({}, data);

        this.model.setModel(data);
        // this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
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

    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        _this.model = new ax5.ui.binder();
        _this.model.setModel({}, _this.target);
    },
});
