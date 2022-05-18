package com.example.tutor_app

class ListTuTor {
    private var id: Int? = null
    var ten: String? = null
    private var monhoc: String? = null
    private var trinhdo: String? = null
    private var anh: String? = null
    private var gioitinh: String? = null
    private var gioithieu: String? = null
    fun getId(): Int? {
        return id
    }
    fun setId(id: Int) {
        this.id = id
    }
    fun getName(): String?{
        return ten
    }
    fun setName(ten: String){
        this.ten = ten
    }
    fun getObject(): String?{
        return monhoc
    }
    fun setObject(mon: String){
        this.monhoc = mon
    }
    fun getImg(): String? {
        return anh
    }
    fun setImg(anh: String){
        this.anh = anh
    }
    fun getGender(): String?{
        return gioitinh
    }
    fun setGender(gt: String){
        this.gioitinh = gt
    }
    fun getIntro(): String? {
        return gioithieu
    }
    fun setIntro(gth: String){
        this.gioithieu = gth
    }
    fun getTrdo(): String? {
        return trinhdo
    }
    fun setTrdo(trinhdo: String){
        this.trinhdo = trinhdo
    }
    fun ListTuTor( id: Int, ten: String, mon: String, trdo:String, anh: String, gt: String, gth: String){
        this.id = id
        this.ten = ten
        this.monhoc = mon
        this.trinhdo = trdo
        this.anh = anh
        this.gioitinh = gt
        this.gioithieu = gth
    }

}