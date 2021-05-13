fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {key: "guestNm", label: "이름", width: 160, align: "center", editor: "text"},
                {key: "guestTel", label: "연락처", width: 200, align: "center", editor: "text"},
                {key: "email", label: "이메일", width: 100, align: "center", editor: "text"},
                {key: "gender", label: "성별", width: 100, align: "center", editor: "text"},
                {key: "brth", label: "생년월일", width: 100, align: "center", editor: "text"},
                {key: "langCd", label: "언어", width: 100, align: "center", editor: "text"},
                {key: "rmk", label: "비고", width: 100, align: "center", editor: "text"}
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, {selectedClear: true});
                }
            }
        });

        axboot.buttonClick(this, "data-grid-view-01-btn", {
            "add": function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            "delete": function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            }
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == "modified" || _type == "deleted") {
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
        this.target.addRow({__created__: true}, "last");
    }
});