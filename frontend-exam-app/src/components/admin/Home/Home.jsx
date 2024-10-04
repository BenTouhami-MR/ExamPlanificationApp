import Box from '@mui/material/Box';
import { Container, Typography, Button } from "@mui/material";
import { motion } from "framer-motion";
import backgroundImage from "../../../assets/Images/img.png";
import { useNavigate } from "react-router-dom"; // Ensure correct path to the background image

export default function Home() {

    const navigate = useNavigate();

    // Motion variants for background animation
    const backgroundVariants = {
        initial: { backgroundPositionX: "0%" }, // Start position (horizontal movement)
        animate: {
            backgroundPositionX: ["0%", "100%"], // Moves the background horizontally
            transition: {
                duration: 10, // Slow down the movement for a smoother experience
                repeat: Infinity,
                ease: "linear"
            }
        }
    };

    // Motion variants for the button hover effect
    const buttonVariants = {
        hover: {
            scale: 1.1,
            borderRadius: 0.8,
            boxShadow: "0px 0px 8px rgb(255, 255, 255)",
            transition: { duration: 0.3, yoyo: Infinity } // Yoyo for continuous scaling animation on hover
        }
    };

    return (
        <Box sx={{ display: "flex", height: "100vh", width: "100vw", justifyContent: "center", alignItems: "center", position: "relative", overflow: "hidden" }}>

            {/* Background image covering the full viewport */}
            <motion.div
                variants={backgroundVariants}
                initial="initial"
                animate="animate"
                style={{
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    width: '100vw',
                    height: '100vh',  // Cover the full viewport height
                    zIndex: -1,
                    backgroundImage: `url(${backgroundImage})`,
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                    backgroundAttachment: 'fixed'
                }}
            />

            {/* Main Content */}
            <Container sx={{ textAlign: 'center', zIndex: 1, color: 'white' }}>
                {/* App Title */}
                <Typography variant="h2" sx={{ fontWeight: 700, mb: 2 }}>
                    Welcome to Exam Scheduler
                </Typography>

                {/* Short Description */}
                <Typography variant="h6" sx={{ maxWidth: '600px', margin: '0 auto', mb: 4 }}>
                    Easily organize and manage your exam schedules. Stay on top of your deadlines and never miss an important exam again!
                </Typography>

                {/* Call to Action Button */}
                <motion.div variants={buttonVariants} whileHover="hover">
                    <Button
                        variant="contained"
                        onClick={() => navigate("/Personnel")}
                        size="large"
                        sx={{
                            bgcolor: '#FF5722', // Custom color for the button
                            color: 'white',
                            fontSize: '1.2rem',
                            fontWeight: 'bold',
                            padding: '12px 24px',
                            borderRadius: '30px',
                            '&:hover': {
                                bgcolor: '#E64A19'
                            }
                        }}
                    >
                        Let's Discover
                    </Button>
                </motion.div>
            </Container>
        </Box>
    );
}
