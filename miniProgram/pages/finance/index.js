//获取应用实例  
var app = getApp()
Page({
  data: {
    financeInfo: [],
    totalAmt: 0
  },
  thisMonth: function () {
    var that = this
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/thisMonth",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        // var data = result.data;
        // if (data == "YES") {
        //   wx.redirectTo({
        //     url: '/pages/finance/index'
        //   })
        // }
        console.log(JSON.stringify(result.data));
        that.setData({
          financeInfo: result.data,
          totalAmt: result.data.totalAmt
        })
      }
    })
  },
  nextMonth: function () {
    var that = this
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/nextMonth",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        // var data = result.data;
        // if (data == "YES") {
        //   wx.redirectTo({
        //     url: '/pages/finance/index'
        //   })
        // }
        console.log(JSON.stringify(result.data));
        that.setData({
          financeInfo: result.data,
          totalAmt: result.data.totalAmt
        })
      }
    })
  },
  findAll: function () {
    var that = this
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/findAll",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        // var data = result.data;
        // if (data == "YES") {
        //   wx.redirectTo({
        //     url: '/pages/finance/index'
        //   })
        // }
        console.log(JSON.stringify(result.data));
        that.setData({
          financeInfo: result.data,
          totalAmt: result.data.totalAmt
        })
      }
    })
  },
  addOne: function () {
    wx.navigateTo({
      url: 'addFinance/addFinance'
    })
  },
  onShow: function () {
    var that = this
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/thisMonth",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        // var data = result.data;
        // if (data == "YES") {
        //   wx.redirectTo({
        //     url: '/pages/finance/index'
        //   })
        // }
        console.log(JSON.stringify(result.data));
        that.setData({
          financeInfo: result.data,
          totalAmt: result.data.totalAmt
        })
      }
    })
  },
  onLoad: function () {
    var that = this
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/thisMonth",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        // var data = result.data;
        // if (data == "YES") {
        //   wx.redirectTo({
        //     url: '/pages/finance/index'
        //   })
        // }
        console.log(JSON.stringify(result.data));
        that.setData({
          financeInfo: result.data,
          totalAmt: result.data.totalAmt
        })
      }
    })
  },
  onShareAppMessage: function () {

    return {

      title: '小程序',

      desc: '测试',

      path: '/pages/index/index',

      imageUrl: 'http://img.hb.aicdn.com/d166ea58f81676521ab0a9c08bb229567c20ef22aa9a-tN6KPk_fw658'

    }

  }
})