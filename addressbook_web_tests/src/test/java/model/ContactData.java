package model;

public record ContactData(String id,
                          String firstName,
                          String middleName,
                          String lastName,
                          String photo,
                          String address,
                          String email,
                          String home,
                          String mobile,
                          String work,
                          String secondary,
                          String email2,
                          String email3) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, address, this.email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, email, this.home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.email, home, this.mobile, this.work, this.secondary, "", "");
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.email, this.home, mobile, this.work, this.secondary, "", "");
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, work, this.secondary, "", "");
    }

    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, secondary, "", "");
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary, this.email2, email3);
    }
}