import React, { useEffect, useState } from "react";
import { Card, CardContent, Typography } from "@mui/material";
import ApiService from "./service/ApiService";

const UserUploadedDetail = () => {
  const [candidate, setCandidate] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const id = localStorage.getItem("id");
        console.log(id);
        
        if (!id) {
          throw new Error("User ID not found in localStorage");
        }
  
        const response = await ApiService.getUserUploadDetails(id);
        console.log("Full API Response:", response);

        if (response.statusCode === 200 && response.uploadedDocuments) {
          setCandidate(response.uploadedDocuments); // Store entire response dynamically
        } else {
          console.error("Invalid API Response:", response);
        }
      } catch (error) {
        console.error("Error Fetching Candidate:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <Typography>Loading...</Typography>;
  if (!candidate || Object.keys(candidate).length === 0) return <Typography>No User Details Available.</Typography>;

  // Define fixed order for sections
  const sectionOrder = ["personal_details", "educational_details", "professional_details"];

  // Function to format attribute names
  const formatAttributeName = (key) => {
    return key
      .replace(/_/g, " ") // Replace underscores with spaces
      .replace(/\b(?:hslc|sslc)\b/gi, (match) => match.toUpperCase()) // Ensure HSLC and SSLC are uppercase
      .replace(/\b\w/g, (char) => char.toUpperCase()); // Capitalize first letter of each word
  };

  return (
    <Card sx={{ maxWidth: 600, margin: "auto", mt: 4, p: 2 }}>
      <CardContent>
        <Typography variant="h5" align="center"> Uploaded Details </Typography>
        <hr/>

        {sectionOrder.map((section) =>
          candidate[section] && (
            <div key={section}>
              <Typography variant="h6" gutterBottom>
                {formatAttributeName(section)}
              </Typography>
              {Object.entries(candidate[section]).map(([key, value]) => (
                <Typography key={key}>
                  <strong>{formatAttributeName(key)}:</strong> {value !== "null" && value !== "0.0" && value != 0 ? value : "Not Entered"}
                </Typography>
              ))}
              <hr />
            </div>
          )
        )}
      </CardContent>
    </Card>
  );
};

export default UserUploadedDetail;
