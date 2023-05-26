package model;

public class Address {
    private int addressId;
    private String province;
    private String district;
    private String ward;

    public Address() {
    }

    public int getAddressId() {
        return addressId;
    }


    public Address(String province, String district, String ward) {
        this.province = province;
        this.district = district;
        this.ward = ward;
    }


    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    @Override
    public String toString() {
        return
                " province : " + province + " "+
                " district : " + district  + " " +
                " ward : " + ward ;
    }
}
