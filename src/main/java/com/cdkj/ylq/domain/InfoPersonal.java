package com.cdkj.ylq.domain;

public class InfoPersonal {

    private InfoBasic basic;

    private InfoOccupation occupation;

    private InfoContact contact;

    public InfoBasic getBasic() {
        return basic;
    }

    public void setBasic(InfoBasic basic) {
        this.basic = basic;
    }

    public InfoOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(InfoOccupation occupation) {
        this.occupation = occupation;
    }

    public InfoContact getContact() {
        return contact;
    }

    public void setContact(InfoContact contact) {
        this.contact = contact;
    }
}
