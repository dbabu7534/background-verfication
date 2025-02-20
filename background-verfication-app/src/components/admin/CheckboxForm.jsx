import React, { useState, useEffect } from "react";
import axios from "axios";
import { object } from "yup";

export const CheckBoxForm = () => {
  const [fieldBox, setFieldBox] = useState({  })

  useEffect(() => {
    // setFieldBox(  )
  }, []);


  const handleCheckBoxChange = (event) => {
    const {id, checked} = event.target;
    setFieldBox ( (prevState) => ({
      ...prevState,
      [id] : checked ? 1 : 0,
    } ) )
  }

  return (
    <div>
      {Object.keys(fieldBox).length > 0 ? (
        Object.keys(fieldBox).map( (key) => (
          <div className="form-check" key = {key}>
        <input
            className = "form-check-input"
            type = "checkbox"
            id = {key}
            checked = { fieldBox[key] === 1 }
            onChange = {handleCheckBoxChange}
        />    
        <label className="form-check-box" htmlFor={key}>
        {key.replace(/([A-Z])/g, " $1")} {/* Format label from camelCase */}

        </label>
        </div>
        ) ) 
      ) : (
        <p> Loading Check Boxes ... </p>
      )
      } 
      
      <button >Submit</button>
    </div>
  );

};

