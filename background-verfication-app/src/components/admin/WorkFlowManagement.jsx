import React, { useState } from "react";
import ApiService from "../service/ApiService";

const WorkFlowManagement = () => {
  const [boxes, setBoxes] = useState([
    { id: 1, name: "personal" },
    { id: 2, name: "professional" },
    { id: 3, name: "upload" },
    { id: 4, name: "educational" },
  ]);

  const [draggedBox, setDraggedBox] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [submittedOrder, setSubmittedOrder] = useState(null);

  const handleDragStart = (event, box) => {
    setDraggedBox(box);
    event.dataTransfer.effectAllowed = "move";
    event.currentTarget.classList.add("dragging");
  };

  const handleDragEnd = (event) => {
    event.currentTarget.classList.remove("dragging");
  };

  const handleDrop = (event, box) => {
    event.preventDefault();
    if (draggedBox.id === box.id) return;

    const newBoxOrder = [...boxes];
    const draggedBoxIndex = newBoxOrder.findIndex((b) => b.id === draggedBox.id);
    const targetBoxIndex = newBoxOrder.findIndex((b) => b.id === box.id);

    newBoxOrder[draggedBoxIndex] = box;
    newBoxOrder[targetBoxIndex] = draggedBox;

    setBoxes(newBoxOrder);
  };

  const handleDragOver = (event) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = "move";
  };

  const handleSubmit = async () => {
    setIsLoading(true);
    setError(null);

    // Send only box names, not positions
    const boxOrder = { boxOrders : boxes.map((box) => ( box.name )) };
    
    console.log(boxOrder);

    try {
      const response = await ApiService.saveWorkFlow(boxOrder);
      
      if( response.statusCode == 200){
        setSubmittedOrder(boxOrder);
        alert("Order saved");
      }

    } catch (error) {
      alert(error);
      console.error( error );
      setError("Failed to save order. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div>
      <div className="container">
        {boxes.map((box) => (
          <div
            key={box.id}
            className="box"
            draggable
            onDragStart={(event) => handleDragStart(event, box)}
            onDragOver={handleDragOver}
            onDrop={(event) => handleDrop(event, box)}
            onDragEnd={handleDragEnd}
          >
            {box.name}
          </div>
        ))}
      </div>
      <div className="submit-container">
        <button className="submit-button" onClick={handleSubmit} disabled={isLoading}>
          {isLoading ? "Saving..." : "Submit"}
        </button>
        {error && <p className="error-message">{error}</p>}
      </div>
{/* 
      {submittedOrder && (
        <div className="order-display">
          <h3>Saved Order:</h3>
          <ul>
            {submittedOrder?.map((item, index) => (
              <li key={index}>
                <strong>{index + 1}.</strong> {item.name}
              </li>
            ))}
          </ul>
        </div> */}
      {/* )} */}
    </div>
  );
};

export default WorkFlowManagement;
