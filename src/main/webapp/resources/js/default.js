// 数据
var _menus = {
    "menus": [{
        "menuid": "1",
        "menuname": "帐号管理",
        "menus": [{
            "menuid": "11",
            "menuname": "帐号管理",
            "url": "userManager"
            },
            // {
            //     "menuid": "12",
            //     "menuname": "个人信息",
            //     "url": "userInfo"
            // }
        ]
    }, {
        "menuid": "2",
        "menuname": "图书管理",
        "menus": [{
            "menuid": "21",
            "menuname": "管理图书",
            "url": "bookManager"
            },

            {
                "menuid": "22",
                "menuname": "管理图书类别",
                "url": "bookCatagoryManager"
            },
            {
                "menuid": "23",
                "menuname": "管理图书书库",
                "url": "bookRoomManager"
            }
        ]
    }, {
        "menuid": "3",
        "menuname": "读者管理",
        "menus": [{
            "menuid": "31",
            "menuname": "借阅证管理",
            "url": "borrowCardManager"
            },
            {
                "menuid": "32",
                "menuname": "借阅等级查看",
                "url": "borrowLevelManager"
            },
            {
                "menuid": "33",
                "menuname": "罚款管理",
                "url": "ticketManager"
            },
            {
                "menuid": "34",
                "menuname": "借书记录查看",
                "url": "borrowManager"
            }
        ]
    }, {
            "menuid": "4",
            "menuname": "借阅管理",
            "menus": [{
                "menuid": "41",
                "menuname": "借书",
                "url": "borrowBookManager"
            },
                {
                    "menuid": "42",
                    "menuname": "还书",
                    "url": "dueBookManager"
                }
            ]
        }
    ]
};
$(function () {
    InitLeftMenu();
})

//初始化左侧
function InitLeftMenu() {
    $("#nav").accordion({animate: true, selected: 999});
    $.each(_menus.menus, function (i, n) {
        var menulist = '';
        menulist += '<ul>';
        $.each(n.menus, function (j, o) {
            menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="' + o.url + '" ><span class="nav">' + o.menuname + '</span></a></div></li> ';
        })
        menulist += '</ul>';
        $('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            selected: false
        });

    });

    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).children('.nav').text();
        var url = $(this).attr("rel");
        var menuid = $(this).attr("ref");
        addTab(tabTitle, url);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });

    // //选中第一个
    // var panels = $('#nav').accordion('panels');
    // var t = panels[0].panel('options').title;
    // $('#nav').accordion('select',t);
}

function addTab(subtitle, url) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
        });
    } else {
        $('#tabs').tabs('select', subtitle);
        $('#mm-tabupdate').click();
    }
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    $.messager.alert(title, msgString, msgType);
}
