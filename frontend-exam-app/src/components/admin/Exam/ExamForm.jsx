import React, { useState, useEffect } from "react";
import {
    Box,
    Button,
    Container,
    FormControl,
    Grid,
    InputLabel,
    MenuItem,
    Paper,
    Select,
    TextField,
    Typography,
    FormHelperText,
    Checkbox,
    FormControlLabel,
    Radio,
    RadioGroup,
} from "@mui/material";
import {TextFields} from "@mui/icons-material";
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";

export default function ExamForm() {
    const [initialData, setInitialData] = useState({});
    const [errors, setErrors] = useState({});
    const [open, setOpen] = useState(false);
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snac
    const [formData, setFormData] = useState({
        elementPedagogique: "",
        typeExam: "",
        cordonnateur: "",
        date: "",
        heureDebut: "",
        PV: "",
        epreuve: "",
        rapportTextuelle: "",
        anneeUniversitaire: "",
        salles: [], // Array of selected salle IDs
        nbreSurveillants: [], // Array of number of surveillants, same length as salles
        semestre: "",
        session: "",
    });

    // Fetch initial data
    const getInitialData = async () => {
        try {
            const response = await fetch("http://localhost:8081/api/admin/exam/initialData");
            if (response.ok) {
                const data = await response.json();
                setInitialData(data);
            } else {
                console.log("Failed to fetch initial data");
            }
        } catch (error) {
            console.log("Error fetching data", error);
        }
    };

    useEffect(() => {
        getInitialData();
    }, []);

    // Handle form field changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    // Handle checkboxes (salles)
    const handleCheckboxChange = (event, salleId) => {
        const isChecked = event.target.checked;

        setFormData((prev) => {
            let updatedSalles = [...prev.salles];
            let updatedSurveillants = [...prev.nbreSurveillants];

            if (isChecked) {
                // Add the salle if checked
                updatedSalles.push(salleId);
                updatedSurveillants.push(1); // Default surveillant count is 1
            } else {
                // Remove the salle if unchecked
                const index = updatedSalles.indexOf(salleId);
                if (index !== -1) {
                    updatedSalles.splice(index, 1);
                    updatedSurveillants.splice(index, 1); // Remove corresponding surveillant count
                }
            }

            return { ...prev, salles: updatedSalles, nbreSurveillants: updatedSurveillants };
        });
    };

    // Handle the number of surveillants input for each salle
    const handleSurveillantChange = (index, value) => {
        setFormData((prev) => {
            const updatedSurveillants = [...prev.nbreSurveillants];
            updatedSurveillants[index] = parseInt(value, 10) || 0; // Ensure it's a number
            return { ...prev, nbreSurveillants: updatedSurveillants };
        });
    };

    // Basic form validation
    const formValidated = () => {
        const newErrors = {};
        if (!formData.elementPedagogique) {
            newErrors.elementPedagogique = "Educational element is required";
        }
        if (!formData.typeExam) {
            newErrors.typeExam = "Exam Type is required";
        }
        if (!formData.cordonnateur) {
            newErrors.cordonnateur = "Coordinator is required";
        }
        if (!formData.date) {
            newErrors.date = "Date is required";
        }
        if (!formData.heureDebut) {
            newErrors.heureDebut = "Start Time is required";
        }
        if (!formData.PV) {
            newErrors.PV = "PV is required";
        }
        if (!formData.epreuve) {
            newErrors.epreuve = "Copy test field is required";
        }
        if (!formData.anneeUniversitaire) {
            newErrors.anneeUniversitaire = "Academic Year is required";
        }
        if (!formData.semestre) {
            newErrors.semestre = "Semester is required";
        }
        if (!formData.session) {
            newErrors.session = "Session is required";
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };


    const handleSubmit =()=>{
        setOpen(true)
    }


// Handle form submission
    const handleConfirmAction = async () => {
        if (formValidated()) {
            console.log(formData);
            try {
                const response = await fetch(`http://localhost:8081/api/admin/exam/create`, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(formData),
                });

                if (response.ok) {
                    const successMessage = await response.text();
                    setSnackbarSeverity("success")
                    setSnackbarMessage(successMessage);
                    setOpen(false);
                    setSnackbarOpen(true);// Display error message from backend // Display success message
                } else {
                    const errorData = await response.json();  // Parse the JSON error response
                    setSnackbarSeverity("error")
                    setSnackbarMessage(`Failed to save exam: ${errorData.message}`);
                    setOpen(false);
                    setSnackbarOpen(true);// Display error message from backend
                }
            } catch (error) {
                console.error("Error saving the exam:", error);
                setSnackbarSeverity("error")
                setSnackbarMessage(`An error occurred while saving the exam.`);
                setOpen(false);
                setSnackbarOpen(true);// Display error message from backend
            }

        }
    };


    return (
        <Container>
            <Paper sx={{ padding: '40px', marginTop: '30px' }}>
                <Typography variant="h5" gutterBottom>
                    Exam Form
                </Typography>

                    <Grid container spacing={3}>
                        {/* Educational Element */}
                        <Grid item xs={6}>
                            <FormControl fullWidth error={!!errors.elementPedagogique}>
                                <InputLabel>Educational Element</InputLabel>
                                <Select
                                    name="elementPedagogique"
                                    value={formData.elementPedagogique}
                                    onChange={handleChange}
                                    label="Educational Element"
                                >
                                    {initialData.listElmPedags?.map((elmt) => (
                                        <MenuItem key={elmt.idElmtPedag} value={elmt.idElmtPedag}>
                                            {elmt.titre}
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.elementPedagogique && (
                                    <FormHelperText>{errors.elementPedagogique}</FormHelperText>
                                )}
                            </FormControl>
                        </Grid>

                        {/* Exam Type */}
                        <Grid item xs={6}>
                            <FormControl fullWidth error={!!errors.typeExam}>
                                <InputLabel>Exam Type</InputLabel>
                                <Select
                                    name="typeExam"
                                    value={formData.typeExam}
                                    onChange={handleChange}
                                    label="Exam Type"
                                >
                                    {initialData.listTypeExams?.map((te) => (
                                        <MenuItem key={te.idTypeExam} value={te.idTypeExam}>
                                            {te.intitule}
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.typeExam && (
                                    <FormHelperText>{errors.typeExam}</FormHelperText>
                                )}
                            </FormControl>
                        </Grid>

                        {/* Coordinator */}
                        <Grid item xs={6}>
                            <FormControl fullWidth error={!!errors.cordonnateur}>
                                <InputLabel>Coordinator</InputLabel>
                                <Select
                                    name="cordonnateur"
                                    value={formData.cordonnateur}
                                    onChange={handleChange}
                                    label="Coordinator"
                                >
                                    {initialData.listenseignants?.map((cord) => (
                                        <MenuItem
                                            key={cord.idPersonnel}
                                            value={cord.idPersonnel}
                                        >
                                            {`${cord.nom} ${cord.prenom}`}  <span style={{color:"red",fontSize:"10px"}}> - {cord.filiere.nomFiliere} | {cord.departement.nomDepartement} -</span>
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.cordonnateur && (
                                    <FormHelperText>{errors.cordonnateur}</FormHelperText>
                                )}
                            </FormControl>
                        </Grid>

                        {/* Date and Start Time */}
                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                type="date"
                                label="Choose the date"
                                name="date"
                                value={formData.date}
                                onChange={handleChange}
                                InputLabelProps={{ shrink: true }}
                                error={!!errors.date}
                                helperText={errors.date}
                            />
                        </Grid>

                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                type="time"
                                label="Start Time"
                                name="heureDebut"
                                value={formData.heureDebut}
                                onChange={handleChange}
                                error={!!errors.heureDebut}
                                helperText={errors.heureDebut}
                            />
                        </Grid>

                        {/* PV and Test */}
                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                label="PV"
                                name="PV"
                                placeholder="PV Path"
                                value={formData.PV}
                                onChange={handleChange}
                                error={!!errors.PV}
                                helperText={errors.PV}
                            />
                        </Grid>

                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                label="Test Copy"
                                name="epreuve"
                                placeholder="Copy Test Path"
                                value={formData.epreuve}
                                onChange={handleChange}
                                error={!!errors.epreuve}
                                helperText={errors.epreuve}
                            />
                        </Grid>



                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                label="Academic Year"
                                name="anneeUniversitaire"
                                value={formData.anneeUniversitaire}
                                onChange={handleChange}
                                error={!!errors.anneeUniversitaire}
                                helperText={errors.anneeUniversitaire}
                            />
                        </Grid>

                        {/* Classrooms and Supervisors */}
                        <Grid item xs={8}>
                            <Typography variant="h6" gutterBottom>
                                Classrooms
                            </Typography>
                            <Box sx={{display:"flex", flexWrap: "wrap"}}>
                                {initialData.listSalles?.map((salle, index) => {
                                    const isChecked = formData.salles.includes(salle.idSalle);
                                    return (
                                        <div key={salle.idSalle} style={{ marginRight: '16px', marginBottom: '16px' }}>
                                            <FormControlLabel
                                                control={
                                                    <Checkbox
                                                        checked={isChecked}
                                                        onChange={(e) => handleCheckboxChange(e, salle.idSalle)}
                                                    />
                                                }
                                                label={salle.nomSalle}
                                            />
                                            {/* Show the number input only if the salle is selected */}
                                            {isChecked && (
                                                <TextField
                                                    fullWidth
                                                    type="number"
                                                    label="Number of Supervisors"
                                                    value={formData.nbreSurveillants[formData.salles.indexOf(salle.idSalle)] || 1}
                                                    onChange={(e) => handleSurveillantChange(formData.salles.indexOf(salle.idSalle), e.target.value)}
                                                    InputLabelProps={{ shrink: true }}
                                                    sx={{ marginBottom: 2 }}
                                                />
                                            )}
                                        </div>
                                    );
                                })}
                            </Box>
                        </Grid>

                        {/* Semester */}
                        <Grid item xs={6}>
                            <Typography variant="h6" gutterBottom>
                                Semester
                            </Typography>
                            <RadioGroup
                                name="semestre"
                                value={formData.semestre}
                                onChange={handleChange}
                            >
                                {initialData.listSmestres?.map((semestre) => (
                                    <FormControlLabel
                                        key={semestre.idSemestre}
                                        value={semestre.idSemestre}
                                        control={<Radio />}
                                        label={semestre.intitule === "printemps" ? "Spring" : "Autumn"}
                                    />
                                ))}
                            </RadioGroup>
                            {errors.semestre && (
                                <FormHelperText error>{errors.semestre}</FormHelperText>
                            )}
                        </Grid>

                        {/* Session */}
                        <Grid item xs={6}>
                            <Typography variant="h6" gutterBottom>
                                Session
                            </Typography>
                            <RadioGroup
                                name="session"
                                value={formData.session}
                                onChange={handleChange}
                            >
                                {initialData.listSessions?.map((session) => (
                                    <FormControlLabel
                                        key={session.idSession}
                                        value={session.idSession}
                                        control={<Radio />}
                                        label={session.intitule === "normal" ? "Normal" : "Retake"}
                                    />
                                ))}
                            </RadioGroup>
                            {errors.session && (
                                <FormHelperText error>{errors.session}</FormHelperText>
                            )}
                        </Grid>
                        {/* Textual Report and Academic Year */}
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                multiline
                                rows={6}
                                label="Textual Report"
                                name="rapportTextuelle"
                                placeholder="Description..."
                                value={formData.rapportTextuelle}
                                onChange={handleChange}
                                error={!!errors.rapportTextuelle}
                                helperText={errors.rapportTextuelle}
                            />
                        </Grid>

                        {/* Submit and Cancel Buttons */}
                        <Grid item xs ={12} sx={{ display: "flex", justifyContent: "flex-end", mt: 3 }}>

                            <Button type="submit" variant="contained" color="primary" sx={{ ml: 2 }} onClick={handleSubmit}>
                                Create
                            </Button>
                        </Grid>
                    </Grid>
                <DialogAndSnackBar handleConfirmAction={()=>handleConfirmAction()}
                                   actionType={"create exam"}
                                   open={open}
                                   setOpen={setOpen}
                                   snackbarOpen={snackbarOpen}
                                   setSnackbarOpen={setSnackbarOpen}
                                   snackbarSeverity={snackbarSeverity}
                                   snackbarMessage={snackbarMessage}

                />

            </Paper>
        </Container>
    );
}
