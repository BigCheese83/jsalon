package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.enums.ScheduleEntryStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ScheduleEntry extends BaseModel {

    private LocalDate regDate;
    private LocalTime regTime;
    private String surname;
    private String name;
    private String phone;
    private Master master;
    private Service service;
    private ScheduleEntryStatus status;
    private String note;

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public LocalTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalTime regTime) {
        this.regTime = regTime;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ScheduleEntryStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleEntryStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduleEntry)) return false;
        if (!super.equals(o)) return false;
        ScheduleEntry that = (ScheduleEntry) o;
        return Objects.equals(regDate, that.regDate) &&
                Objects.equals(regTime, that.regTime) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(master, that.master) &&
                Objects.equals(service, that.service) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), regDate, regTime, surname, name, phone, master, service, status);
    }

    @Override
    public String toString() {
        return "ScheduleEntry{" + super.toString() + ", " +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                (master != null ? ", master=" + master : "") +
                (service != null ? ", service=" + service : "") +
                ", regDate=" + regDate +
                ", regTime=" + regTime +
                ", status=" + status +
                '}';
    }
}
