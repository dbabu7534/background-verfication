import React, { useState, useEffect } from "react";
import ApiService from "../service/ApiService";
import axios from "axios"; // Import axios if not imported already
import { useNavigate } from "react-router-dom";

const PersonalField = () => {
    const [fieldBox, setFieldBox] = useState([]);
    const navigate = useNavigate();
    

    useEffect( () => {
        const fetchData = async () => {
            try {
                const response = await ApiService.getPersonalField();
                // console.log(response.data);

                if (response.status === 200 && response.data.personalField) {
                    setFieldBox(response.data.personalField);
                }
            } catch (error) {
                console.error("Error fetching checkbox data:", error);
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

            //   console.log("Submitting Data:", fieldBox); // Log the data before sending
          
            const response = await ApiService.putPersonalField(fieldBox);
            
            if (response.status === 200) {
                alert("Data successfully submitted!");
                navigate('/field-management')
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
                fieldBox.map( (fieldObj) => (
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
            <button onClick={handleSubmit}>Submit</button> {/* Call the submit function */}
        </div>
    );
};

export default PersonalField;