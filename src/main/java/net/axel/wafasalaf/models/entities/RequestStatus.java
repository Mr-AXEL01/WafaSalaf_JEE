package net.axel.wafasalaf.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "requestStatus")
public class RequestStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @NotBlank(message = "Date is required")
    @Column(name = "changedAt", nullable = false)
    private LocalDate changedAt;

    @Column(name = "description")
    private String description;

    public RequestStatus() {
    }

    public RequestStatus(UUID id, Request request, Status status, LocalDate changedAt, String description) {
        this.id = id;
        this.request = request;
        this.status = status;
        this.changedAt = changedAt;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDate changedAt) {
        this.changedAt = changedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RequestStatus{" +
                "id=" + id +
                ", request=" + request +
                ", status=" + status +
                ", changedAt=" + changedAt +
                ", description='" + description + '\'' +
                '}';
    }
}
