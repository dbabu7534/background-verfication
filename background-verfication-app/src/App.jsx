import "./App.css";

import "../node_modules/bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import RegisterForm from "./components/RegisterFrom";
import LoginPage from "./components/Login";
import PersonalDetail from "./components/PersonalDetail";
import EducationDetail from "./components/EducationDetail";
import ProfessionalDetail from "./components/ProfessionalDetail";

import WorkFlowManagement from "./components/admin/WorkFlowManagement";
import { CheckBoxForm } from "./components/admin/CheckboxForm";

import AdminDefault from "./components/admin/AdminDefault";
import FieldManagement from "./components/admin/FieldManagement";
import PersonalField from "./components/admin/PersonalField";
import ProfessionalField from "./components/admin/ProfessionalField";
import EducationalField from "./components/admin/EducationalField";
import Status from "./components/admin/Status";
import Test from "./components/admin/Test";
import UserUploadedDetail from "./components/UserUploadedDetail";
import FileUploadPage from "./components/FileUploadPage";
import HomePage from "./components/HomePage";
import Navbar from "./components/header/Navbar";
import Home from "./components/header/Home";
import AllCandidates from "./components/admin/AllCandidates";
import TemplateManager from "./components/admin/TemplateManager";
function App() {
  return (
    <div>
      <Router>

      <Navbar />

        <Routes>
          <Route path='/home' element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterForm />} />
          
          <Route path="/personal" element={<PersonalDetail />} />
          <Route path="/professional" element={<ProfessionalDetail />} />
          <Route path="/educational" element={<EducationDetail />} />
          <Route path="/upload" element={<FileUploadPage />} />
          <Route path="/upload-details" element={<UserUploadedDetail />} />


          <Route path="/" element={<Home/>} />
          <Route path="/management" element={<AdminDefault />} />
          {/* <Route path="/" element={<AdminPage/>} /> */}
          <Route path="/workflow" element={<WorkFlowManagement />} />

          <Route path="/check-box" element={<CheckBoxForm />} />
          <Route path="/field-management" element={<FieldManagement />} />

          <Route path="/personal-field" element={<PersonalField />} />
          <Route path="/professional-field" element={<ProfessionalField />} />
          <Route path="/educational-field" element={<EducationalField />} />
          <Route path="/status-candidates" element={<Status />} />
          <Route path="/candidates" element={<AllCandidates />} />
          <Route path="/test" element={<Test />} />
          <Route path="/template-management" element={<TemplateManager/>} />


        </Routes>
      </Router>
    </div>
  );
}

export default App;
