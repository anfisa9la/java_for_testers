package model;

public record ContactData(String id, String firstName, String middleName, String lastName, String photo, String address, String email) {

    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.photo, this.address, this.email);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.photo, this.address, this.email);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.photo, this.address, this.email);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, lastName, this.photo, this.address, this.email);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, photo, this.address, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, address, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.address, email);
    }
}