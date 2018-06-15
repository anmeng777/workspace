// pages/finance/addFinance/addFinance.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    array: ['8.5%', '8.0%', '9.0%', '10%'],
    userarray: ['王会轻', '刘松'],
    periodarray: ['3年', '1年', '5年'],
    index: 0,
    date: '2018-06-15',
    lilv: "8.5%",
    riqi: '2018-06-15',
    user: "王会轻",
    period: "3年",
    totalAmt: '',
    ticketNo: '',
    addhidden: false,
    financeid: 0
  },
  bindPickerChange: function (e) {
    this.setData({
      lilv: this.data.array[e.detail.value]
    })
  },
  bindDateChange: function (e) {
    this.setData({
      riqi: e.detail.value
    })
  },
  bindUserChange: function (e) {
    this.setData({
      user: this.data.userarray[e.detail.value]
    })
  },
  bindPeriodChange: function (e) {
    this.setData({
      period: this.data.periodarray[e.detail.value]
    })
  },
  addOne: function () {
    var that = this.data
    var period = that.period.replace("年","");
    var lilv = that.lilv.replace("%","");
    lilv = parseFloat(lilv)/100;
    var totalAmt = parseInt(that.totalAmt)
    console.log(totalAmt)
    if (!that.totalAmt || !that.ticketNo || that.totalAmt == null){
      wx.showToast({
          title: '没填金额或票号',
          icon: 'loading',
          duration: 3000
      });
      return false;
      console.log("asdasdad")
    }
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/addOne",
      data: {
        user: that.user,
        startDate: that.riqi,
        period: period,
        rate: lilv,
        ticketNo: that.ticketNo,
        totalAmt: that.totalAmt
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
          wx.showToast({
            title: '成功',
            icon: 'success',
            duration: 3000
          });
          setTimeout(function () {
            wx.navigateBack() 
          }, 1000)
      }
    })

  },
  edit: function () {
    var that = this.data
    var period = that.period.replace("年", "");
    var lilv = that.lilv.replace("%", "");
    lilv = parseFloat(lilv) / 100;
    var totalAmt = parseInt(that.totalAmt)
    console.log(totalAmt)
    if (!that.ticketNo || that.totalAmt == null) {
      wx.showToast({
        title: '没填金额或票号',
        icon: 'loading',
        duration: 3000
      });
      return false;
    }

    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/edit",
      data: {
        id: that.financeid,
        user: that.user,
        startDate: that.riqi,
        period: period,
        rate: lilv,
        ticketNo: that.ticketNo,
        totalAmt: that.totalAmt
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 3000
        });
        setTimeout(function () {
          wx.navigateBack()
        }, 1000)
      }
    })

  },
  totalAmtInput: function (e) {
    this.setData({
      totalAmt: e.detail.value
    })
  },
  ticketNoInput: function (e) {
    this.setData({
      ticketNo: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (!options.id){
      return false;
    }
    this.setData({
      addhidden: true,
      financeid: options.id
    })
    console.log(options.id)
    console.log(this.data.financeid)
    wx.request({
      url: "https://16u882035y.51mypc.cn/autoCheckin/finance/getOne",
      data: {
        id: options.id
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'POST',
      //服务端的回掉  
      success: function (result) {
        console.log(JSON.stringify(result))
        result = result.data.data;
        that.setData({
          lilv: result.rate*100+"%",
          riqi: result.startDate,
          user: result.user,
          period: result.period+"年",
          totalAmt: result.totalAmt,
          ticketNo: result.ticketNo
        })
      }
    })



  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})