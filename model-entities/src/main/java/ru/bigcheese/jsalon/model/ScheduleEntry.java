package ru.bigcheese.jsalon.model;

import ru.bigcheese.jsalon.model.enums.ScheduleEntryStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "schedule")
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

    @Id
    @SequenceGenerator(name = "scheduleSeq", sequenceName = "schedule_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduleSeq")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "reg_date", nullable = false)
    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Column(name = "reg_time", nullable = false)
    public LocalTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalTime regTime) {
        this.regTime = regTime;
    }

    @Column(nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne
    @JoinColumn(name = "master_id")
    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
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
        return Objects.equals(getRegDate(), that.getRegDate()) &&
                Objects.equals(getRegTime(), that.getRegTime()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getMaster(), that.getMaster()) &&
                Objects.equals(getService(), that.getService()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRegDate(), getRegTime(), getSurname(), getName(), getPhone(), getMaster(), getService(), getStatus());
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
