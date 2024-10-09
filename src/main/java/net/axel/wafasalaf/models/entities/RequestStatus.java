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
    Request request;

    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;

    @NotBlank(message = "Date is required")
    @Column(name = "date", nullable = false)
    private LocalDate changedAt;

    public RequestStatus() {
    }

    public RequestStatus(UUID id, Request request, Status status, LocalDate changedAt) {
        this.id = id;
        this.request = request;
        this.status = status;
        this.changedAt = changedAt;
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

    @Override
    public String toString() {
        return "RequestStatus{" +
                "id=" + id +
                ", request=" + request +
                ", status=" + status +
                ", changedAt=" + changedAt +
                '}';
    }
}
