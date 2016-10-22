$(function () {
    var postCardInfo = {
        canMove: true,
        currentDate: {
            "picturePath": "pic/1.jpg",
            "oppositePath": "",
            "productSize": {"width": 148, "height": 100},
            "name": "美少女",
            "cropBox": {
                "size": {"width": 0, "height": 0},
                "location": {"x": 0, "y": 0}
            },
            "copy": 1,
            "id": 1,
            "pictureBox": {
                "size": {"width": 0, "height": 0},
                "location": {"x": 0, "y": 0}
            },
            "state": 0,
            "type": {
                "padding": {
                    "top": 0, "left": 0,
                    "bottom": 0, "right": 0
                },
                "name": "A版",
                "aspectRatio": 0,
                "id": 1,
                "autoRotate": true
            }
        },
        positionDelta: {
            deltaX: 0,
            deltaY: 0
        },
        mouserPosition: {
            x: 0,
            y: 0
        },
        currentDivSize: {
            width: 0,
            height: 0
        },
        checkSize: function () {
            if (tmpMyBox.width() != postCardInfo.currentDivSize.width || tmpMyBox.height() != postCardInfo.currentDivSize.height) {
                console.log("尺寸修改事件");
                postCardInfo.setCropBox(postCardInfo.currentDate.cropBox.size.width, postCardInfo.currentDate.cropBox.size.height);
                postCardInfo.currentDivSize.width = tmpMyBox.width();
                postCardInfo.currentDivSize.height = tmpMyBox.height();
            }
        },
        showPic: function () {
            var tmpCropBox = $("#cropBox");
            var tmpMyPicture = $("#pic");

            if (postCardInfo.currentDate.pictureBox.location.x > tmpCropBox.position().left) {
                postCardInfo.currentDate.pictureBox.location.x = tmpCropBox.position().left;
            }
            if (postCardInfo.currentDate.pictureBox.location.y > tmpCropBox.position().top) {
                postCardInfo.currentDate.pictureBox.location.y = tmpCropBox.position().top;
            }
            tmpMyPicture.css({
                "left": postCardInfo.currentDate.pictureBox.location.x,
                "top": postCardInfo.currentDate.pictureBox.location.y
            });
            if (tmpMyPicture.position().left + tmpMyPicture.width() < tmpCropBox.position().left + tmpCropBox.width()) {
                postCardInfo.currentDate.pictureBox.location.x = tmpCropBox.width() - tmpMyPicture.width() + tmpCropBox.position().left;
                tmpMyPicture.css({
                    "left": postCardInfo.currentDate.pictureBox.location.x
                });
            }
            if (tmpMyPicture.position().top + tmpMyPicture.height() < tmpCropBox.position().top + tmpCropBox.height()) {
                postCardInfo.currentDate.pictureBox.location.y = tmpCropBox.height() - tmpMyPicture.height() + tmpCropBox.position().top;
                tmpMyPicture.css({
                    "top": postCardInfo.currentDate.pictureBox.location.y
                });

            }
            //如果宽度小于裁切框
            if (tmpMyPicture.width() < tmpCropBox.width()) {
                tmpMyPicture.css({
                    "left": tmpCropBox.position().left + (tmpCropBox.width() - tmpMyPicture.width()) / 2 + "px"
                })
            }
            //如果高度小于裁切框
            if (tmpMyPicture.height() < tmpCropBox.height()) {
                tmpMyPicture.css({
                    "top": tmpCropBox.position().top + (tmpCropBox.height() - tmpMyPicture.height()) / 2 + "px"
                })
            }
            //刷新一下阴框
            this.cropBox_refresh();
        },
        picture_zoomSize: function (e) {

            var picture = $("#pic");
            var cropBox = $("#cropBox");
            var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox
            if (delta > 0) {
                if (picture.width() / picture.height() > cropBox.width() / cropBox.height()) {
                    picture.css({
                        "width": picture.width() + 20
                    })
                } else {
                    picture.css({
                        "height": picture.height() + 20
                    })
                }
            } else if (delta < 0) {
                if (picture.width() / picture.height() > cropBox.width() / cropBox.height()) {
                    picture.css({
                        "width": picture.width() - 20
                    })
                } else {
                    picture.css({
                        "height": picture.height() - 20
                    })
                }
            }
            postCardInfo.checkPictureSize();
            postCardInfo.showPic();
        }, checkPictureSize: function () {

            var picture = $("#pic");
            var cropBox = $("#cropBox");
            if (picture.width() < cropBox.width() && picture.height() < cropBox.height()) {
                //如果上下一定有白边
                if (picture.width() / picture.height() > cropBox.width() / cropBox.height()) {
                    picture.css({
                        "width": cropBox.width()
                    })
                } else {
                    picture.css({
                        "height": cropBox.height()
                    })
                }
            } else if (picture.width() > cropBox.width() && picture.height() > cropBox.height()) {
                if (picture.width() / picture.height() < cropBox.width() / cropBox.height()) {
                    picture.css({
                        "height": cropBox.width() * picture.height() / picture.width()
                    })
                } else {
                    picture.css({
                        "width": cropBox.height() * picture.width() / picture.height()
                    })
                }
            }
            postCardInfo.currentDate.pictureBox.size.width = picture.width();
            postCardInfo.currentDate.pictureBox.size.height = picture.height();
        },
        picture_move: function (e) {
            postCardInfo.checkSize()
            postCardInfo.mouserPosition.x = e.pageX;
            postCardInfo.mouserPosition.y = e.pageY;
            if (postCardInfo.canMove) {
                postCardInfo.currentDate.pictureBox.location.x = e.pageX - postCardInfo.positionDelta.deltaX;
                postCardInfo.currentDate.pictureBox.location.y = e.pageY - postCardInfo.positionDelta.deltaY;
                postCardInfo.showPic();
            }
            postCardInfo.positionDelta.deltaX = e.pageX - postCardInfo.currentDate.pictureBox.location.x;
            postCardInfo.positionDelta.deltaY = e.pageY - postCardInfo.currentDate.pictureBox.location.y;

        },
//      cropBox_refresh: function () {
//          var pictureContainer = $("#MyContext");
//          //所有的阴影框
//          var cropBoxShade = $(".cropBox");
//          var cropBox = $("#cropBox");
//          cropBoxShade.css({//设置所有遮罩属性
////              "position": "relative"
//          });
//          cropBoxShade.eq(0).css({
//              "width": (pictureContainer.width() - cropBox.width()) / 2,
//              "left": 0,
//              "top": 0
//          });
//          cropBoxShade.eq(1).css({
//              "width": (pictureContainer.width() - cropBox.width()) / 2,
//              "right": 0,
//              "top": 0
//          });
//          cropBoxShade.eq(2).css({
//              "width": cropBox.width(),
//              "height": (pictureContainer.height() - cropBox.height()) / 2 + "px",
//              "left": cropBox.position().left,
//              "top": 0
//          });
//          cropBoxShade.eq(3).css({
//              "width": cropBox.width() + "px",
//              "height": (pictureContainer.height() - cropBox.height()) / 2 + "px",
//              "left": cropBox.position().left,
//              "bottom": 0
//          });
//      },
        init: function () {
            $("#info").html(
                "<br/><h1 id='MyHeader'>" + postCardInfo.currentDate.name + "</h1>" +
                "<h2>版式：" + postCardInfo.currentDate.type.name + "</h2>" +
                "<h2>尺寸：" + postCardInfo.currentDate.productSize.width + "×" + postCardInfo.currentDate.productSize.height + "</h2>" +
                "<h2>图片：" + postCardInfo.currentDate.picturePath + "</h2>"
            );

            var tmpMyPicture = $("#pic");
            var tmpMyCropBox = $("#cropBox");
            postCardInfo.cropBox_refresh();
            tmpMyPicture.css({
                "width": "auto",
                "height": "auto"
            });
            //调整一下图片的尺寸
            //如果图片的宽度比较大的话，适应高度
            if (tmpMyPicture.width() / tmpMyPicture.height() > tmpMyCropBox.width() / tmpMyCropBox.height()) {
                tmpMyPicture.css({
                    "width": tmpMyCropBox.height() * tmpMyPicture.width() / tmpMyPicture.height()
                });
                postCardInfo.currentDate.pictureBox.location.x = tmpMyCropBox.position().left;
            } else {
                tmpMyPicture.css({
                    "height": tmpMyCropBox.width() * tmpMyPicture.height() / tmpMyPicture.width()
                });
                postCardInfo.currentDate.pictureBox.location.y = tmpMyCropBox.position().top;

            }
            postCardInfo.checkPictureSize();
            postCardInfo.showPic();
        },
        setCropBox: function (tmpWidth, tmpHeight) {
            var tmpMainForm = $("#MainForm");
            var tmpCropBox = $("#cropBox");
            tmpCropBox.css({
                width: tmpMainForm.width() - 200,
                height: (tmpMainForm.width() - 200) * tmpHeight / tmpWidth
            });
            if (tmpCropBox.height() + 200 > tmpMainForm.height()) {
                tmpCropBox.css({
                    height: tmpMainForm.height() - 200,
                    width: (tmpMainForm.height() - 200) * tmpWidth / tmpHeight
                });
            }
            tmpCropBox.css({
                left: (tmpMainForm.width() - tmpCropBox.width()) / 2,
                top: (tmpMainForm.height() - tmpCropBox.height()) / 2
            });
            postCardInfo.init();
            postCardInfo.showPic();
            //初始化CropBox之后，保存此信息CropBox的信息
            postCardInfo.currentDate.cropBox.size.width = tmpCropBox.width();
            postCardInfo.currentDate.cropBox.size.height = tmpCropBox.height();
            postCardInfo.currentDate.cropBox.location.x = tmpCropBox.position().left;
            postCardInfo.currentDate.cropBox.location.y = tmpCropBox.position().top;
            //初始化鼠标偏移值
            postCardInfo.positionDelta.deltaX = postCardInfo.mouserPosition.x - postCardInfo.currentDate.pictureBox.location.x;
            postCardInfo.positionDelta.deltaY = postCardInfo.mouserPosition.y - postCardInfo.currentDate.pictureBox.location.y;
        },
        submitMyPostCard: function () {
            var tmpCropBox = $("#cropBox");
            var tmpPicture = $("#pic");
            postCardInfo.currentDate.cropBox.location.x = tmpCropBox.position().left;
            postCardInfo.currentDate.cropBox.location.y = tmpCropBox.position().top;
            postCardInfo.currentDate.cropBox.size.width = tmpCropBox.width();
            postCardInfo.currentDate.cropBox.size.height = tmpCropBox.height();
            // postCardInfo.currentDate.pictureBox.location.x = tmpPicture.position().left;
            // postCardInfo.currentDate.pictureBox.location.y = tmpPicture.position().top;
            // postCardInfo.currentDate.pictureBox.size.width = tmpPicture.width();
            // postCardInfo.currentDate.pictureBox.size.height = tmpPicture.height();
            $.ajax({
                url: "http://119.29.85.66:8080/post/postCard/upload",
                type: "post",
                success: postCardInfo.setAllInfo,
                dataType: "json",
                data: JSON.stringify(postCardInfo.currentDate)
            });
        },
        setAllInfo: function (data) {
            postCardInfo.currentDate = data;
            console.log(JSON.stringify(data));
            var tmpData = postCardInfo.currentDate;
            postCardInfo.setCropBox(tmpData.cropBox.size.width, tmpData.cropBox.size.height);
            $("#pic").attr("src", "http://119.29.85.66:8080/post/" + tmpData.picturePath).css({
                "width": "auto",
                "height": "auto"
            });
            postCardInfo.init();
            // setTimeout(postCardInfo.init, 1);
            postCardInfo.showPic();

        }
    };
    var tmpMyBox = $("#MyBox");
//绑定事件
    setInterval(postCardInfo.checkSize, 10);
    tmpMyBox
        .css({cursor: "move"})
        .bind("mousemove", postCardInfo.picture_move)
        .click(postCardInfo.submitMyPostCard)
        .on("mousewheel", postCardInfo.picture_zoomSize);
    $("img").load(function () {
        postCardInfo.init();
        console.log($("#MyContext"));
        // $("#MyContext").show(100);
    });
//  $.ajax({
//      url: "http://119.29.85.66:8080/post/postCard/upload",
//      type: "get",
//      success: postCardInfo.setAllInfo,
//      dataType: "json"
//  });
    //初始化各项信息
    $(document).keydown(function (event) {
        if (event.keyCode == 32) {
            postCardInfo.canMove = false;
            tmpMyBox.css({cursor: "pointer"})
            tmpMyBox.css({cursor: ""});
        }
        event.stopPropagation();
    }).bind("keyup", function (event) {
        if (event.keyCode == 32) {
            postCardInfo.canMove = true;
            tmpMyBox.css({cursor: "move"});
        }
        event.stopPropagation();
    });
})
;
