import React from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useForm } from "react-hook-form";
import { useNavigate, useLocation } from "react-router-dom";
import ApiService from "./service/ApiService";

const EducationDetail = () => {
  let {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm();

  const navigate = useNavigate();
  const location = useLocation();
  const remainingQueue  = location.state?.remainingQueue || []; // Get remaining queue


  const handleData = async (data) => {
    try {
      //Call the register method from ApiService
      const id = localStorage.getItem("id");

      if (!id) {
        throw new Error("User ID not found in localStorage");
      }
      const response = await ApiService.saveEducational(id, {
        sslcSchoolName: data.sslcSchoolName,
        sslcPercentage: data.sslcPercentage,
        hslcSchoolName:data.hslcSchoolName,
        hslcPercentage:data.hslcPercentage,
        collegeName:data.collegeName,
        universityRollNumber:data.universityRollNumber,
        finalCgpa:data.finalCgpa
      } );
      

      if (response.statusCode === 200) {
        // Success message and redirection
        reset(); // Clear form fields
        alert("Personal Details Filled successfully");
         

        if(remainingQueue.length > 0) {
          const nextPage = remainingQueue.shift(); // Get next page          
          navigate(`/${nextPage}`, { state: { remainingQueue } }); // Pass updated queue
        } else{
          navigate('/upload-details')
        }
    //    navigate("/professional"); // Redirect to login or home page
      }
    } catch (error) {
      alert(
        error.response?.data?.message ||
          "Something went wrong. Please try again later."
      );
    }
  };

  return (
    <div className="form-container">
      <h1> Educational Details </h1>

      <Form onSubmit={handleSubmit(handleData)}>
        <Form.Group className="mb-3" controlId="formBasicFirstName">
          <Form.Label>Enter SSLC School Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="10th School Name"
            {...register("sslcSchoolName")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicDob">
          <Form.Label>10th Percentage</Form.Label>
          <Form.Control
            type="number"
            step="0.1"
            placeholder="Enter your Percentage"
            {...register("sslcPercentage")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicFirstName">
          <Form.Label>Enter HSLC School Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="12th School Name"
            {...register("hslcSchoolName")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicDob">
          <Form.Label>12th Percentage</Form.Label>
          <Form.Control
            type="number"
            step="0.1"
            placeholder="Enter your percentage"
            {...register("hslcPercentage")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicLastName">
          <Form.Label>College Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your college name"
            {...register("collegeName")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>University Roll Number</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Your University Roll Number"
            {...register("universityRollNumber")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicLastName">
          <Form.Label>CGPA Percentage</Form.Label>
          <Form.Control
            type="number"
            step="0.1"
            placeholder="Enter your CGPA Percentage"
            {...register("finalCgpa")}
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Save
        </Button>
      </Form>
    </div>
  );
};

export default EducationDetail;