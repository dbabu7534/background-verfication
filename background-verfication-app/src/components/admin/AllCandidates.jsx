// import React, { useEffect, useState } from "react";
// import {
//   Card,
//   CardContent,
//   Typography,
//   Avatar,
//   Grid,
//   Button,
//   Dialog,
//   DialogTitle,
//   DialogContent,
//   DialogActions,
//   IconButton,
// } from "@mui/material";
// import { Email, Close, CheckCircle, Cancel, PendingActions } from "@mui/icons-material";
// import ApiService from "../service/ApiService";

// const AllCandidates = () => {
//   const [open, setOpen] = useState(false);
//   const [selectedCandidate, setSelectedCandidate] = useState(null);
//   const [modalContent, setModalContent] = useState("");
//   const [status, setStatus] = useState({});
//   const [candidates, setCandidates] = useState([]);

//   useEffect(() => {
//     let isMounted = true;
//     const fetchData = async () => {
//       try {
//         const response = await ApiService.getAllUsers();
//         if (response.status === 200 && isMounted) {
//           setCandidates(response.data.getAllUsers);
//         }
//       } catch (error) {
//         console.error("Error Fetching Candidates:", error);
//       }
//     };
//     fetchData();
//     return () => {
//       isMounted = false;
//     };
//   }, []);

//   const handleOpen = (candidate, content) => {
//     setSelectedCandidate(candidate);
//     setModalContent(content);
//     setOpen(true);
//   };

//   const handleClose = () => setOpen(false);

//   const updateStatus = async (userId, newStatus) => {
//     try {
//       const response = await ApiService.updateCandidateStatus({
//         candidateId: userId,
//         status: newStatus,
//       });
//       if (response.status === 200) {
//         setStatus((prevStatus) => ({ ...prevStatus, [userId]: newStatus }));
//       }
//     } catch (error) {
//       console.error("Error updating candidate status:", error);
//     }
//   };

//   const displayValue = (value) => (value === null || value === 0 || value === 0.0 ? "Not Entered" : value);

//   const renderDetails = (details) =>
//     details ? (
//       Object.entries(details).map(([key, value]) => (
//         <Typography key={key}>{`${key.replace(/([A-Z])/g, " $1").trim()}: ${displayValue(value)}`}</Typography>
//       ))
//     ) : (
//       <Typography>Not Entered</Typography>
//     );

//   return (
//     <Grid container spacing={3} justifyContent="center" style={{ marginTop: 20 }}>
//       {candidates.map((candidate) => (
//         <Grid item key={candidate.userId}>
//           <Card sx={{ maxWidth: 350, padding: 2, borderRadius: 3, boxShadow: 3 }}>
//             <CardContent style={{ textAlign: "center" }}>
//               <Avatar sx={{ width: 100, height: 100, margin: "auto", mb: 2 }}>
//                 {candidate.firstName[0]}{candidate.lastName[0]}
//               </Avatar>
//               <Typography variant="h5" fontWeight="bold">{candidate.firstName} {candidate.lastName}</Typography>
//               <Typography color="text.secondary">
//                 <Email fontSize="small" style={{ verticalAlign: "middle", marginRight: 5 }} />{candidate.email}
//               </Typography>
//               {["pending", "selected", "rejected"].map((statusType) => (
//                 <Button
//                   key={statusType}
//                   variant="contained"
//                   color={status[candidate.userId] === statusType ? (statusType === "selected" ? "success" : statusType === "pending" ? "warning" : "error") : "primary"}
//                   fullWidth
//                   sx={{ borderRadius: 2, mt: 1 }}
//                   onClick={() => updateStatus(candidate.userId, statusType)}
//                 >
//                   {statusType === "pending" && <PendingActions fontSize="small" style={{ marginRight: 5 }} />}
//                   {statusType === "selected" && <CheckCircle fontSize="small" style={{ marginRight: 5 }} />}
//                   {statusType === "rejected" && <Cancel fontSize="small" style={{ marginRight: 5 }} />}
//                   {statusType.charAt(0).toUpperCase() + statusType.slice(1)}
//                 </Button>
//               ))}
//               { ["personalDetail", "professionalDetail", "educationalDetail"] .map ((detailType) => (
//                 <Button
//                   key={detailType}
//                   variant="outlined"
//                   fullWidth
//                   sx={{ borderRadius: 2, mt: 1 }}
//                   onClick={() => handleOpen(candidate, detailType)}
//                 >
//                   {detailType.replace("Detail", " Details")}
//                 </Button>
//               ))}
//             </CardContent>
//           </Card>
//         </Grid>
//       ))}
//       <Dialog open={open} onClose={handleClose}>
//         <DialogTitle>
//           {modalContent.replace("Detail", " Details")}
//           <IconButton aria-label="close" onClick={handleClose} sx={{ position: "absolute", right: 8, top: 8 }}>
//             <Close />
//           </IconButton>
//         </DialogTitle>
//         <DialogContent dividers>
//           {selectedCandidate && renderDetails(selectedCandidate[modalContent])}
//         </DialogContent>
//         <DialogActions>
//           <Button onClick={handleClose} color="secondary">Close</Button>
//         </DialogActions>
//       </Dialog>
//     </Grid>
//   );
// };

// export default AllCandidates;



import React, { useEffect, useState } from "react";
import {
  Card,
  CardContent,
  Typography,
  Avatar,
  Grid,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  IconButton,
} from "@mui/material";
import { Email, Close, CheckCircle, Cancel, PendingActions, InsertDriveFile } from "@mui/icons-material";
import ApiService from "../service/ApiService";

const AllCandidates = () => {
  const [open, setOpen] = useState(false);
  const [selectedCandidate, setSelectedCandidate] = useState(null);
  const [modalContent, setModalContent] = useState("");
  const [status, setStatus] = useState({});
  const [candidates, setCandidates] = useState([]);

  useEffect(() => {
    let isMounted = true;
    const fetchData = async () => {
      try {
        const response = await ApiService.getAllUsers();
        if (response.status === 200 && isMounted) {
          setCandidates(response.data.getAllUsers);
        }
      } catch (error) {
        console.error("Error Fetching Candidates:", error);
      }
    };
    fetchData();
    return () => {
      isMounted = false;
    };
  }, []);

  const handleOpen = (candidate, content) => {
    setSelectedCandidate(candidate);
    setModalContent(content);
    setOpen(true);
  };

  const handleClose = () => setOpen(false);

  const updateStatus = async (userId, newStatus) => {
    try {
      const response = await ApiService.updateCandidateStatus({
        candidateId: userId,
        status: newStatus,
      });
      if (response.status === 200) {
        setStatus((prevStatus) => ({ ...prevStatus, [userId]: newStatus }));
      }
    } catch (error) {
      console.error("Error updating candidate status:", error);
    }
  };

  const displayValue = (value) => (value === null || value === 0 || value === 0.0 ? "Not Entered" : value);

  const renderDetails = (details) =>
    details ? (
      Object.entries(details).map(([key, value]) => (
        <Typography key={key}>{`${key.replace(/([A-Z])/g, " $1").trim()}: ${displayValue(value)}`}</Typography>
      ))
    ) : (
      <Typography>Not Entered</Typography>
    );

  const renderDocuments = (documents) =>
    documents && documents.length > 0 ? (
      documents.map((doc, index) => (
        <Typography key={index}>
          <InsertDriveFile fontSize="small" style={{ marginRight: 5 }} />
          <a href={doc.url} target="_blank" rel="noopener noreferrer">{doc.name}</a>
        </Typography>
      ))
    ) : (
      <Typography>No documents uploaded</Typography>
    );

  return (
    <Grid container spacing={3} justifyContent="center" style={{ marginTop: 20 }}>
      {candidates.map((candidate) => (
        <Grid item key={candidate.userId}>
          <Card sx={{ maxWidth: 350, padding: 2, borderRadius: 3, boxShadow: 3 }}>
            <CardContent style={{ textAlign: "center" }}>
              <Avatar sx={{ width: 100, height: 100, margin: "auto", mb: 2 }}>
                {candidate.firstName[0]}{candidate.lastName[0]}
              </Avatar>
              <Typography variant="h5" fontWeight="bold">{candidate.firstName} {candidate.lastName}</Typography>
              <Typography color="text.secondary">
                <Email fontSize="small" style={{ verticalAlign: "middle", marginRight: 5 }} />{candidate.email}
              </Typography>
              {["pending", "selected", "rejected"].map((statusType) => (
                <Button
                  key={statusType}
                  variant="contained"
                  color={status[candidate.userId] === statusType ? (statusType === "selected" ? "success" : statusType === "pending" ? "warning" : "error") : "primary"}
                  fullWidth
                  sx={{ borderRadius: 2, mt: 1 }}
                  onClick={() => updateStatus(candidate.userId, statusType)}
                >
                  {statusType === "pending" && <PendingActions fontSize="small" style={{ marginRight: 5 }} />}
                  {statusType === "selected" && <CheckCircle fontSize="small" style={{ marginRight: 5 }} />}
                  {statusType === "rejected" && <Cancel fontSize="small" style={{ marginRight: 5 }} />}
                  {statusType.charAt(0).toUpperCase() + statusType.slice(1)}
                </Button>
              ))}
              {["personalDetail", "professionalDetail", "educationalDetail", "uploadedDocuments"].map((detailType) => (
                <Button
                  key={detailType}
                  variant="outlined"
                  fullWidth
                  sx={{ borderRadius: 2, mt: 1 }}
                  onClick={() => handleOpen(candidate, detailType)}
                >
                  {detailType.replace("Detail", " Details").replace("uploadedDocuments", "Uploaded Documents")}
                </Button>
              ))}
            </CardContent>
          </Card>
        </Grid>
      ))}
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>
          {modalContent.replace("Detail", " Details").replace("uploadedDocuments", "Uploaded Documents")}
          <IconButton aria-label="close" onClick={handleClose} sx={{ position: "absolute", right: 8, top: 8 }}>
            <Close />
          </IconButton>
        </DialogTitle>
        <DialogContent dividers>
          {selectedCandidate &&
            (modalContent === "uploadedDocuments"
              ? renderDocuments(selectedCandidate[modalContent])
              : renderDetails(selectedCandidate[modalContent]))}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="secondary">Close</Button>
        </DialogActions>
      </Dialog>
    </Grid>
  );
};

export default AllCandidates;
