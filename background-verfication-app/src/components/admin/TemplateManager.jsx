import React, { useState, useEffect } from "react";

const TemplateManager = () => {
  const [bgColor, setBgColor] = useState("#ffffff");
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const role = localStorage.getItem("role");
    setIsAdmin(role === "ADMIN");
  }, []);

  const handleColorChange = (event) => {
    if (isAdmin) {
      setBgColor(event.target.value);
    }
  };

  return (
    <div style={{ backgroundColor: bgColor, minHeight: "100vh", padding: "20px" }}>
      <h1>Template Management</h1>
      {isAdmin ? (
        <>
          <label>Select Background Color: </label>
          <input type="color" value={bgColor} onChange={handleColorChange} />
        </>
      ) : (
        <p>Only Admin can change the background color.</p>
      )}
    </div>
  );
};

export default TemplateManager;
