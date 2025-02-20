import React from "react";
import { Box, Typography } from "@mui/material";

const Home = () => {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "90vh", // Takes full screen height
        textAlign: "center",
      }}
    >
      <Typography variant="h4" fontWeight="bold">
        Background Verification Application
      </Typography>
    </Box>
  );
};

export default Home;
