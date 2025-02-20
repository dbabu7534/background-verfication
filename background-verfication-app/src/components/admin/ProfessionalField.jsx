import React, { useState, useEffect } from "react";
import ApiService from "../service/ApiService";

const ProfessionalField = () => {
  const [fieldBox, setFieldBox] = useState([]);

  useEffect( () => {
          const fetchData = async () => {
              try {
                  const response = await ApiService.getProfessionalField();
                  console.log(response.data);
  
                  if (response.status === 200 && response.data.professionalField) {
                      setFieldBox(response.data.professionalField);
                  }
              } catch (error) {
                  console.error("Error fetching checkbox Professional data:", error);
              }
          };
  
          fetchData();
      }, []);
  
  
  
      const handleCheckBoxChange = (event) => {
          const { id, checked } = event.target;
  
          setFieldBox((prevState) =>
              prevState.map((field) =>
                  field.field === id ? { ...field, isActive: checked } : field
              )
          );
      };
  
      // ✅ Submit handler to send updated checkbox states to the backend
      const handleSubmit = async () => {
          try {
              
              const response = await ApiService.putProfessionalField(fieldBox);
  
              if (response.status === 200) {
                  alert("Data successfully submitted!");
              } else {
                  alert("Submission failed.");
              }
          } catch (error) {
              console.error("Error submitting data:", error);
              alert("An error occurred while submitting data.");
          }
      };

  return (
    <div>
      {fieldBox.length > 0 ? (
        fieldBox.map((fieldObj) => (
          <div className="form-check" key={fieldObj.field}>
            <input
              className="form-check-input"
              type="checkbox"
              id={fieldObj.field}
              checked={fieldObj.isActive}
              onChange={handleCheckBoxChange}
            />
            <label className="form-check-label" htmlFor={fieldObj.field}>
              {fieldObj.field.replace(/_/g, " ")}
            </label>
          </div>
        ))
      ) : (
        <p>Loading Checkboxes...</p>
      )}
      <button onClick={handleSubmit}>Submit</button>{" "}
      {/* Call the submit function */}
    </div>
  );
};

export default ProfessionalField;
