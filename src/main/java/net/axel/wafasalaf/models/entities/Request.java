package net.axel.wafasalaf.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import net.axel.wafasalaf.models.enums.Civility;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "requests")
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Project is required")
    @Column(name = "project", nullable = false)
    private String project;

    @NotBlank(message = "Work is required")
    @Column(name = "work", nullable = false)
    private String work;

    @Max(value = 600000, message = "Loan must not pass 600000")
    @Min(value = 5000, message = "Loan must be at least 5000")
    @Column(name = "amount_loan", nullable = false)
    private double amountLoan;

    @Max(value = 120, message = "duration must not pass 120")
    @Min(value = 12, message = "duration must be at least 12")
    @Column(name = "duration", nullable = false)
    private int duration;

    @Min(value = 1, message = "Monthly payment must be positive")
    @Column(name = "monthly", nullable = false)
    private double monthly;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(06|07|\\+212)[0-9]{8}$", message = "Invalid number please enter a valid moroccan number.")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull(message = "Civility is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "civility", nullable = false)
    private Civility civility;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name= "last_name", nullable = false)
    private String last_name;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name= "first_name", nullable = false)
    private String first_name;

    @NotBlank(message = "CIN is required.")
    @Column(name = "cin", nullable = false)
    private String cin;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be a past date")
    @Column(name = "birthDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @NotNull(message = "Hiring day is required")
    @Past(message = "Hiring day must be a past date")
    @Column(name = "hiring_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate hiringDate;

    @Min(value = 1, message = "Income must be positive")
    @Column(name = "income", nullable = false)
    private double income;

    @Column(name = "have_credit", nullable = false)
    private boolean haveCredit;

    @OneToMany(mappedBy = "requests")
    Set<RequestStatus> requestStatuses;

    public Request() {
    }

    public Request(String project, String work, double amountLoan, int duration, double monthly, String email, String phone, Civility civility, String last_name, String first_name, String cin, LocalDate birthDate, LocalDate hiringDate, double income, boolean haveCredit) {
        this.project = project;
        this.work = work;
        this.amountLoan = amountLoan;
        this.duration = duration;
        this.monthly = monthly;
        this.email = email;
        this.phone = phone;
        this.civility = civility;
        this.last_name = last_name;
        this.first_name = first_name;
        this.cin = cin;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.income = income;
        this.haveCredit = haveCredit;
    }

    public Request(UUID id, String project, String work, double amountLoan, int duration, double monthly, String email, String phone, Civility civility, String last_name, String first_name, String cin, LocalDate birthDate, LocalDate hiringDate, double income, boolean haveCredit) {
        this(project, work, amountLoan, duration, monthly, email, phone, civility, last_name, first_name, cin, birthDate, hiringDate, income, haveCredit);
        this.id = id;
    }

    public Request(UUID id, String project, String work, double amountLoan, int duration, double monthly, String email, String phone, Civility civility, String last_name, String first_name, String cin, LocalDate birthDate, LocalDate hiringDate, double income, boolean haveCredit, Set<RequestStatus> requestStatuses) {
        this(id, project, work, amountLoan, duration, monthly, email, phone, civility, last_name, first_name, cin, birthDate, hiringDate, income, haveCredit);
        this.requestStatuses = requestStatuses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public double getAmountLoan() {
        return amountLoan;
    }

    public void setAmountLoan(double amountLoan) {
        this.amountLoan = amountLoan;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public boolean isHaveCredit() {
        return haveCredit;
    }

    public void setHaveCredit(boolean haveCredit) {
        this.haveCredit = haveCredit;
    }

    public Set<RequestStatus> getRequestStatuses() {
        return requestStatuses;
    }

    public void setRequestStatuses(Set<RequestStatus> requestStatuses) {
        this.requestStatuses = requestStatuses;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", work='" + work + '\'' +
                ", amountLoan=" + amountLoan +
                ", duration=" + duration +
                ", monthly=" + monthly +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", civility=" + civility +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", cin='" + cin + '\'' +
                ", birthDate=" + birthDate +
                ", hiringDate=" + hiringDate +
                ", income=" + income +
                ", haveCredit=" + haveCredit +
                '}';
    }
}
