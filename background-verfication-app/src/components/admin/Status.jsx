import React, { useState, useEffect } from "react";
import ApiService from "../service/ApiService";

const Status = () => {
  const [candidates, setCandidates] = useState([]);
  const [filter, setFilter] = useState("All");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCandidates = async () => {
      try {
        const response = await ApiService.allUserStatus();
        if (response.status === 200) {
          setCandidates(response.data.candidateStatus);
        } else {
          throw new Error("Failed to fetch data");
        }
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchCandidates();
  }, []);

  const filteredCandidates =
    filter === "All" ? candidates : candidates.filter((c) => c.status === filter);

  return (
    <div style={styles.page}>
      <h1 style={styles.header}>Candidate Status Page</h1>
      <StatusFilter onFilter={setFilter} />

      {loading && <p>Loading candidates...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
      {!loading && !error && <CandidateList candidates={filteredCandidates} />}
    </div>
  );
};

const StatusFilter = ({ onFilter }) => {
  const statuses = ["All", "pending", "selected", "rejected"];

  return (
    <div style={styles.filterContainer}>
      {statuses.map((status) => (
        <button key={status} onClick={() => onFilter(status)} style={styles.filterButton}>
          {status.charAt(0).toUpperCase() + status.slice(1)}
        </button>
      ))}
    </div>
  );
};

const CandidateList = ({ candidates }) => {
  return (
    <div style={styles.listContainer}>
      {candidates.length > 0 ? (
        candidates.map((candidate) => <CandidateCard key={candidate.email} candidate={candidate} />)
      ) : (
        <p>No candidates found...</p>
      )}
    </div>
  );
};

const CandidateCard = ({ candidate }) => {
  return (
    <div style={styles.card}>
      <h3 style={styles.cardName}>{candidate.name}</h3>
      <p style={styles.cardText}>Email: {candidate.email}</p>
      <p style={styles.cardText}>
        Status: <span style={styles.statusText}>{candidate.status}</span>
      </p>
    </div>
  );
};

const styles = {
  page: {
    padding: "20px",
    fontFamily: "Arial, sans-serif",
    backgroundColor: "#f5f5f5",
    minHeight: "100vh",
  },
  header: {
    textAlign: "center",
    color: "#333",
    marginBottom: "20px",
  },
  filterContainer: {
    display: "flex",
    justifyContent: "center",
    gap: "10px",
    marginBottom: "20px",
  },
  filterButton: {
    padding: "10px 20px",
    border: "none",
    borderRadius: "5px",
    backgroundColor: "#007bff",
    color: "#fff",
    cursor: "pointer",
    fontSize: "16px",
  },
  listContainer: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    gap: "10px",
  },
  card: {
    width: "300px",
    padding: "15px",
    border: "1px solid #ccc",
    borderRadius: "5px",
    backgroundColor: "#fff",
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
  },
  cardName: {
    margin: "0 0 10px 0",
    color: "#333",
  },
  cardText: {
    margin: "5px 0",
    color: "#555",
  },
  statusText: {
    fontWeight: "bold",
    color: "#007bff",
  },
};

export default Status;
