import React from 'react';
import { Container, Button, Row, Col } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import './HomePage.css'; // Custom CSS for styling

const HomePage = () => {
  const navigate = useNavigate();

  return (
    <div className="home-page">
      {/* Hero Section */}
      <div className="hero-section">
        <Container>
          <Row className="justify-content-center align-items-center">
            <Col md={8} className="text-center">
              <h1 className="hero-title" >Background Verification App</h1>
              <div className="cta-buttons">
                <Button variant="secondary" size="lg" onClick={() => navigate('/login')}>
                  Login
                </Button>
                <Button variant="secondary" size="lg" onClick={() => navigate('/register')} className="ms-3">
                  Register
                </Button>
              </div>
            </Col>
          </Row>
        </Container>
      </div>
      
    </div>
  );
};

export default HomePage;