import React from "react";

const AdminPage = () => {
  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="w-1/2 p-4 bg-white rounded-2xl shadow-lg">
        <h2 className="text-xl font-semibold mb-4 text-center">Admin Page</h2>
        <div className="h-64 overflow-y-auto border rounded-lg p-4 bg-gray-50">
          <div className="mb-4 p-3 bg-white rounded shadow">
            <h3 className="text-lg font-medium">Work Flow Management</h3>
            <p className="text-gray-600">Manage and optimize workflow processes.</p>
          </div>
          <div className="mb-4 p-3 bg-white rounded shadow">
            <h3 className="text-lg font-medium">Field Management</h3>
            <p className="text-gray-600">Customize fields for data entry and user input.</p>
          </div>
          <div className="mb-4 p-3 bg-white rounded shadow">
            <h3 className="text-lg font-medium">Template Management</h3>
            <p className="text-gray-600">Create and modify document templates.</p>
          </div>
        </div>
        <div className="mt-4 flex justify-center">
          <button className="px-4 py-2 bg-blue-500 text-white rounded-lg shadow hover:bg-blue-600">Submit</button>
        </div>
      </div>
    </div>
  );
};

export default AdminPage;
