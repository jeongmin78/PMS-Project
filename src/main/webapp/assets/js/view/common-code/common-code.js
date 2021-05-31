var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var searchData = caller.searchView.getData();
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/commoncodegroup',
            data: searchData,
            callback: function (res) {
                caller.treeView01.setData(searchData, res.list, data);
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var formData = caller.formView01.getData();
        var obj = {
            list: caller.treeView01.getData(),
            deletedList: caller.treeView01.getDeletedList(),
        };
        console.log(obj);
        var groupId;
        if (formData.groupId) groupId = '/' + formData.groupId;
        else groupId = '';
        axboot
            .call({
                type: 'PUT',
                url: '/api/v1/commoncodegroup',
                data: JSON.stringify(obj),
                callback: function (res) {
                    caller.treeView01.clearDeletedList();
                    axToast.push('공통코드 카테고리가 저장 되었습니다');
                },
            })
            // .call({
            //     type: 'PUT',
            //     url: '/api/v1/commoncodegroup' + groupId,
            //     data: JSON.stringify(formData),
            //     callback: function (res) {},
            // })
            .done(function () {
                if (data && data.callback) {
                    data.callback();
                } else {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { groupId: caller.formView01.getData().groupId });
                }
            });

        var groupId;
        if (formData.groupId) {
            groupId = '/' + formData.groupId;
            axboot.ajax({
                type: 'PUT',
                url: '/api/v1/commoncodegroup' + groupId,
                data: JSON.stringify(formData),
                callback: function (res) {
                    console.log(res);
                    caller.formView01.clear();
                    caller.gridView01.clear();
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { groupId: caller.formView01.getData().groupId });
                },
            });
        }
    },
    TREEITEM_CLICK: function (caller, act, data) {
        console.log(data);
        if (typeof data.groupId === 'undefined') {
            caller.formView01.clear();
            if (confirm('신규 생성된 코드 그룹은 저장 후 편집 할수 있습니다. 지금 저장 하시겠습니까?')) {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            }
            return;
        }

        caller.formView01.clear();
        caller.gridView01.clear();
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/commoncodegroup/' + data.groupId,
            data: {},
            callback: function (res) {
                caller.formView01.setData(res);
            },
        });
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/commonCodes/' + data.groupCd,
            data: {},
            callback: function (res) {
                caller.gridView01.setData(res);
            },
        });
    },
    TREEITEM_DESELECTE: function (caller, act, data) {
        caller.formView01.clear();
    },
    TREE_ROOTNODE_ADD: function (caller, act, data) {
        caller.treeView01.addRootNode();
    },
    SEARCH_AUTH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: ['menu', 'auth'],
            data: data,
            callback: function (res) {
                var list = [];
                if (res.program) {
                    caller.formView01.authGroup.forEach(function (g) {
                        var item = {
                            grpAuthCd: g.grpAuthCd,
                            grpAuthNm: g.grpAuthNm,
                            useYn: 'N',
                            schAh: 'N',
                            savAh: 'N',
                            exlAh: 'N',
                            delAh: 'N',
                            fn1Ah: 'N',
                            fn2Ah: 'N',
                            fn3Ah: 'N',
                            fn4Ah: 'N',
                            fn5Ah: 'N',
                            menuId: data.menuId,
                        };

                        res.list.forEach(function (n) {
                            if (n.grpAuthCd == item.grpAuthCd) {
                                $.extend(item, {
                                    useYn: 'Y',
                                    schAh: n.schAh || 'N',
                                    savAh: n.savAh || 'N',
                                    exlAh: n.exlAh || 'N',
                                    delAh: n.delAh || 'N',
                                    fn1Ah: n.fn1Ah || 'N',
                                    fn2Ah: n.fn2Ah || 'N',
                                    fn3Ah: n.fn3Ah || 'N',
                                    fn4Ah: n.fn4Ah || 'N',
                                    fn5Ah: n.fn5Ah || 'N',
                                });
                            }
                        });

                        if (res.program) {
                            $.extend(item, {
                                program_schAh: res.program.schAh || 'N',
                                program_savAh: res.program.savAh || 'N',
                                program_exlAh: res.program.exlAh || 'N',
                                program_delAh: res.program.delAh || 'N',
                                program_fn1Ah: res.program.fn1Ah || 'N',
                                program_fn2Ah: res.program.fn2Ah || 'N',
                                program_fn3Ah: res.program.fn3Ah || 'N',
                                program_fn4Ah: res.program.fn4Ah || 'N',
                                program_fn5Ah: res.program.fn5Ah || 'N',
                            });
                        }
                        list.push(item);
                    });
                }
                caller.gridView01.setData(list);
            },
        });
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
    },
    ITEM_SAVE: function (caller, act, data) {
        var saveList = [].concat(caller.gridView01.getData());
        saveList = saveList.concat(caller.gridView01.getData('deleted'));

        axboot.ajax({
            type: 'PUT',
            url: '/api/v1/commonCodes',
            data: JSON.stringify(saveList),
            callback: function (res) {
                // ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            },
        });
    },
});
var CODE = {};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    var _this = this;

    axboot
        .call({
            type: 'GET',
            url: '/api/v1/commoncodegroup',
            data: '',
            callback: function (res) {
                var groupList = [];
                res.list.forEach(function (n) {
                    groupList.push({
                        value: n.groupCd,
                        text: n.groupNm + '(' + n.groupCd + ')',
                        groupCd: n.groupCd,
                        groupNm: n.groupNm,
                        data: n,
                    });
                });
                this.groupList = groupList;
            },
        })
        .done(function () {
            CODE = this; // this는 call을 통해 수집된 데이터들.

            _this.pageButtonView.initView();
            _this.searchView.initView();
            _this.treeView01.initView();
            _this.formView01.initView();
            _this.gridView01.initView();

            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });
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
        this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');
        this.rootCd;
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            rootCd: 'SYSTEM_MANAGER',
        };
    },
});

/**
 * treeView
 */
fnObj.treeView01 = axboot.viewExtend(axboot.treeView, {
    param: {},
    deletedList: [],
    newCount: 0,
    addRootNode: function () {
        var _this = this;
        var nodes = _this.target.zTree.getSelectedNodes();
        var treeNode = nodes[0];

        // root
        treeNode = _this.target.zTree.addNodes(null, {
            id: '_isnew_' + ++_this.newCount,
            pId: _this.param.groupId || 0,
            name: 'New Item',
            __created__: true,
            rootCd: _this.param.groupCd,
        });

        if (treeNode) {
            _this.target.zTree.editName(treeNode[0]);
        }
        fnObj.treeView01.deselectNode();
    },
    initView: function () {
        var _this = this;

        $('[data-tree-view-01-btn]').click(function () {
            var _act = this.getAttribute('data-tree-view-01-btn');
            switch (_act) {
                case 'add':
                    ACTIONS.dispatch(ACTIONS.TREE_ROOTNODE_ADD);
                    break;
                case 'save':
                    ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
                    break;
            }
        });

        this.target = axboot.treeBuilder(
            $('[data-z-tree="tree-view-01"]'),
            {
                view: {
                    dblClickExpand: false,
                    addHoverDom: function (treeId, treeNode) {
                        var sObj = $('#' + treeNode.tId + '_span');
                        if (treeNode.editNameFlag || $('#addBtn_' + treeNode.tId).length > 0) return;
                        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
                        sObj.after(addStr);
                        var btn = $('#addBtn_' + treeNode.tId);
                        if (btn) {
                            btn.bind('click', function () {
                                _this.target.zTree.addNodes(treeNode, {
                                    id: '_isnew_' + ++_this.newCount,
                                    pId: treeNode.id,
                                    name: 'New Item',
                                    __created__: true,
                                    rootCd: _this.target.zNodes[0].groupCd, //
                                });

                                _this.target.zTree.selectNode(treeNode.children[treeNode.children.length - 1]);
                                _this.target.editName();
                                fnObj.treeView01.deselectNode();

                                return false;
                            });
                        }
                    },
                    removeHoverDom: function (treeId, treeNode) {
                        $('#addBtn_' + treeNode.tId)
                            .unbind()
                            .remove();
                    },
                },
                edit: {
                    enable: true,
                    editNameSelectAll: true,
                },
                callback: {
                    beforeDrag: function () {
                        //return false;
                    },
                    onClick: function (e, treeId, treeNode, isCancel) {
                        ACTIONS.dispatch(ACTIONS.TREEITEM_CLICK, treeNode);
                    },
                    onRename: function (e, treeId, treeNode, isCancel) {
                        treeNode.__modified__ = true;
                    },
                    onRemove: function (e, treeId, treeNode, isCancel) {
                        if (!treeNode.__created__) {
                            treeNode.__deleted__ = true;
                            _this.deletedList.push(treeNode);
                        }
                        fnObj.treeView01.deselectNode();
                    },
                },
            },
            []
        );
    },
    setData: function (_searchData, _tree, _data) {
        this.param = $.extend({}, _searchData);
        this.target.setData(_tree);

        if (_data && typeof _data.groupId !== 'undefined') {
            // selectNode
            (function (_tree, _keyName, _key) {
                var nodes = _tree.getNodes();
                var findNode = function (_arr) {
                    var i = _arr.length;
                    while (i--) {
                        if (_arr[i][_keyName] == _key) {
                            _tree.selectNode(_arr[i]);
                        }
                        if (_arr[i].children && _arr[i].children.length > 0) {
                            findNode(_arr[i].children);
                        }
                    }
                };
                findNode(nodes);
            })(this.target.zTree, 'groupId', _data.groupId);
        }
    },
    getData: function () {
        var _this = this;
        var tree = this.target.getData();

        var convertList = function (_tree) {
            var _newTree = [];
            _tree.forEach(function (n, nidx) {
                var item = {};
                if (n.__created__ || n.__modified__) {
                    item = {
                        __created__: n.__created__,
                        __modified__: n.__modified__,
                        groupId: n.groupId,
                        rootCd: _this.param.rootCd,
                        groupNm: n.groupNm, //
                        parentId: n.pId,
                        sort: nidx,
                        groupCd: n.name,
                        level: n.level,
                        multiLanguageJson: n.multiLanguageJson,
                    };
                } else {
                    item = {
                        groupId: n.groupId,
                        rootCd: n.rootCd,
                        groupNm: n.groupNm, //
                        parentId: n.parentId,
                        sort: nidx,
                        groupCd: n.name,
                        level: n.level,
                        multiLanguageJson: n.multiLanguageJson,
                    };
                }
                if (n.children && n.children.length) {
                    item.children = convertList(n.children);
                }

                _newTree.push(item);
            });
            return _newTree;
        };
        var newTree = convertList(tree);
        return newTree;
    },
    getDeletedList: function () {
        return this.deletedList;
    },
    clearDeletedList: function () {
        this.deletedList = [];
        return true;
    },
    updateNode: function (data) {
        var treeNodes = this.target.getSelectedNodes();
        if (treeNodes[0]) {
            treeNodes[0].groupCd = data.groupCd;
        }
    },
    deselectNode: function () {
        ACTIONS.dispatch(ACTIONS.TREEITEM_DESELECTE);
    },
});

/**
 * formView01
 */
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return $.extend({}, axboot.formView.defaultData, {
            multiLanguageJson: {
                ko: '',
                en: '',
            },
        });
    },
    initView: function () {
        var _this = this;
        this.commonCodeList = CODE.commonCodeList;

        this.target = $('.js-form');
        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
        this.mask = new ax5.ui.mask({
            theme: 'form-mask',
            target: $('#split-panel-form'),
            content: COL('ax.admin.menu.form.d1'),
        });
        this.mask.open();

        this.initEvent();

        axboot.buttonClick(this, 'data-form-view-01-btn', {
            'form-clear': function () {
                ACTIONS.dispatch(ACTIONS.FORM_CLEAR);
            },
        });
    },
    initEvent: function () {
        var _this = this;
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        return data;
    },
    setData: function (data) {
        this.mask.close();

        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
    },
    clear: function () {
        this.mask.open();
        this.model.setModel(this.getDefaultData());
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showLineNumber: false,
            frozenColumnIndex: 0,
            showRowSelector: true,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                //menuId
                { key: 'groupCd', label: '분류코드', width: 160, align: 'center' },
                { key: 'groupNm', label: '분류명', width: 100, align: 'center' },
                { key: 'code', label: '코드', width: 80, align: 'center', editor: 'text' },
                { key: 'name', label: '코드값', width: 100, align: 'center', editor: 'text' },
                { key: 'sort', label: '정렬', width: 80, align: 'center', editor: 'text' },
                { key: 'useYn', label: '사용여부', width: 80, align: 'center' },
                { key: 'rmk', label: '비고', width: 100, align: 'center', editor: 'text' },
                /// --> 이것들을 list로 담아서  [PUT] "/api/v2/menu/auth"
            ],
            body: {
                onClick: function () {
                    // this.self.select(this.dindex, { selectedClear: true });
                    // ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
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
            save: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_SAVE);
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                return this.progNm && this.progPh;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        var formData = fnObj.formView01.getData();
        this.target.addRow(
            { __created__: true, useYn: 'Y', groupCd: formData.groupCd, groupNm: formData.groupNm, groupId: formData.groupId },
            'last'
        );
    },
});
