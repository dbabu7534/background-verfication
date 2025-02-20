import React, { useState } from "react";
import { Card,CardContent,Typography,Button,Box,CircularProgress,Snackbar,Alert, } from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { useLocation, useNavigate } from "react-router-dom";
import ApiService from "./service/ApiService";

const FileUploadPage = () => {
  const [file, setFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [uploadSuccess, setUploadSuccess] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();
  const location = useLocation();

  const remainingQueue  = location.state?.remainingQueue || []; 


  const handleFileChange = (event) => {
    const selectedFile = event.target.files[0];

    if (!selectedFile) return;

    if (selectedFile.size > 5 * 1024 * 1024) {
      setErrorMessage("File size must be less than 5MB.");
      return;
    }

    setFile(selectedFile);
  };

  const handleUpload = async () => {
    if (!file) {
      setErrorMessage("Please select a file first.");
    }

    const id = localStorage.getItem("id");


    if (!id) {
        throw new Error("User ID not found in localStorage");
    }

    setUploading(true);

    try {
      const response = await ApiService.uploadFile(id, file); // Pass file to uploadFile
  
      if (response.status === 200) {
        setUploadSuccess(true);
        setFile(null);
  
        if (remainingQueue.length > 0) {
          const nextPage = remainingQueue.shift(); // Get next page
          navigate(`/${nextPage}`, { state: { remainingQueue } }); // Pass updated queue
        } else {
          navigate('/status');
        }
      } else {
        setErrorMessage("File upload failed. Please try again.");
      }
    } catch (error) {
      setErrorMessage("An error occurred during file upload.");
      console.error(error);
    } finally {
      setUploading(false);
    }
  };

  return (
    <Card sx={{ maxWidth: 500, margin: "auto", mt: 5, p: 3, textAlign: "center", boxShadow: 3 }}>
      <CardContent>
        <Typography variant="h5" gutterBottom>
          Upload Your File
        </Typography>

        {/* File Upload Box */}
        <Box
          sx={{
            border: "2px dashed #aaa",
            borderRadius: 2,
            padding: 3,
            cursor: "pointer",
            "&:hover": { borderColor: "primary.main" },
          }}
          onClick={() => document.getElementById("fileInput").click()}
        >
          <CloudUploadIcon sx={{ fontSize: 40, color: "primary.main" }} />
          <Typography variant="body1">Drag & Drop or Click to Upload</Typography>
        </Box>

        {/* Hidden Input Field */}
        <input
          type="file"
          id="fileInput"
          style={{ display: "none" }}
          onChange={handleFileChange}
        />

        {/* File Details */}
        {file && (
          <Box mt={2} p={2} sx={{ backgroundColor: "#f9f9f9", borderRadius: 1 }}>
            <Typography variant="body1"><strong>File Name:</strong> {file.name}</Typography>
            <Typography variant="body1"><strong>Size:</strong> {(file.size / 1024).toFixed(2)} KB</Typography>
            <Typography variant="body1"><strong>Type:</strong> {file.type || "Unknown"}</Typography>
          </Box>
        )}

        {/* Upload Button & Progress */}
        <Box mt={2}>
          <Button
            variant="contained"
            color="primary"
            startIcon={<CloudUploadIcon />}
            onClick={handleUpload}
            disabled={uploading}
          >
            {uploading ? "Uploading..." : "Upload File"}
          </Button>

          {uploading && <CircularProgress size={24} sx={{ ml: 2 }} />}
        </Box>

        {/* Success Message */}
        <Snackbar open={uploadSuccess} autoHideDuration={3000} onClose={() => setUploadSuccess(false)}>
          <Alert severity="success">File uploaded successfully!</Alert>
        </Snackbar>

        {/* Error Message */}
        <Snackbar open={!!errorMessage} autoHideDuration={3000} onClose={() => setErrorMessage("")}>
          <Alert severity="error">{errorMessage}</Alert>
        </Snackbar>
      </CardContent>
    </Card>
  );
};

export default FileUploadPage;
