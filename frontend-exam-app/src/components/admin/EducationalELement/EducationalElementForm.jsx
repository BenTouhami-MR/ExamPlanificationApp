import React, { useState, useEffect, useRef } from "react";
import { Box, Button, Container, FormControl, Grid, InputLabel, MenuItem, Paper, Select, TextField, Typography } from "@mui/material";
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";

export default function EducationalElementForm() {
    const [open, setOpen] = useState(false);
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [initialData, setInitialData] = useState({}); // Store initial data from API
    const [errors, setErrors] = useState({});

    const titleRef = useRef('');
    const typeElementRef = useRef('');
    const coordinatorRef = useRef('');
    const teacherRef = useRef('');
    const levelRef = useRef('');

    const handleClickOpen = () => {
        setOpen(true);
    };

    const formValidated = () => {
        const newErrors = {};
        if (!titleRef.current.value) {
            newErrors.title = "Title is required";
        }
        if (!typeElementRef.current.value) {
            newErrors.typeElement = "Element Type is required";
        }
        if (!coordinatorRef.current.value) {
            newErrors.coordinator = "Coordinator is required";
        }
        if (!teacherRef.current.value) {
            newErrors.teacher = "Teacher is required";
        }
        if (!levelRef.current.value) {
            newErrors.level = "Level is required";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const sendElementToBeSaved = async (elementData) => {
        console.log(elementData)
        try {
            const response = await fetch(`http://localhost:8081/api/admin/elementsPed/addElmPedag`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(elementData)
            });

            if (response.ok) {
                setSnackbarMessage(await response.text());
                setSnackbarSeverity("success");
                setSnackbarOpen(true);
            } else {
                setSnackbarMessage("Failed to save the element");
                setSnackbarSeverity("error");
                setSnackbarOpen(true);
            }
        } catch (error) {
            setSnackbarMessage("An error occurred while saving the element");
            setSnackbarSeverity("error");
            setSnackbarOpen(true);
        }
    };

    const handleSubmit = () => {
        if (formValidated()) {
            const elementData = {
                titre: titleRef.current.value,
                idTypeElm: typeElementRef.current.value,
                idCordinateur: coordinatorRef.current.value,
                idEnseignant: teacherRef.current.value,
                idNiveau: levelRef.current.value
            };

            sendElementToBeSaved(elementData);
        }
        setOpen(false);
    };

    const getInitialData = async () => {
        try {
            const response = await fetch(`http://localhost:8081/api/admin/elementsPed/initialData`, {
                method: 'GET',
            });
            if (response.ok) {
                setInitialData(await response.json());
            } else {
                console.log("Failed to fetch initial data");
            }
        } catch (error) {
            console.log("An error occurred while fetching initial data");
        }
    };

    useEffect(() => {
        getInitialData();
    }, []);

    return (
        <>
            <Box height={30} />
            <Container sx={{ display: "flex", justifyContent: "center", alignItems: "center", marginTop: "60px" }}>
                <Paper sx={{ padding: '60px' ,minWidth:1300 }}>
                    <Typography variant="h5" gutterBottom>
                        Educational Element Form
                    </Typography>
                    <Grid container spacing={4}>
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                label="Title"
                                name="Title"
                                inputRef={titleRef}
                                error={!!errors.title}
                                helperText={errors.title}
                                variant="outlined"
                                margin="dense"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <FormControl fullWidth margin="dense">
                                <InputLabel>Element Type</InputLabel>
                                <Select
                                    name="ElementType"
                                    inputRef={typeElementRef}
                                    label="Element Type"
                                    error={!!errors.typeElement}
                                >
                                    {initialData.typeElementList?.map((type) => (
                                        <MenuItem key={type.idTypeElm} value={type.idTypeElm}>
                                            {type.type}
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.typeElement && <Typography color="error">{errors.typeElement}</Typography>}
                            </FormControl>
                        </Grid>
                        <Grid item xs={6}>
                            <FormControl fullWidth margin="dense">
                                <InputLabel>Coordinator</InputLabel>
                                <Select
                                    name="Coordinator"
                                    inputRef={coordinatorRef}
                                    label="Coordinator"
                                    error={!!errors.coordinator}
                                >
                                    {initialData.enseignantsList?.map((ens) => (
                                        <MenuItem key={ens.idPersonnel} value={ens.idPersonnel}>
                                            {`${ens.nom} ${ens.prenom}`}
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.coordinator && <Typography color="error">{errors.coordinator}</Typography>}
                            </FormControl>
                        </Grid>
                        <Grid item xs={6}>
                            <FormControl fullWidth margin="dense">
                                <InputLabel>Teacher</InputLabel>
                                <Select
                                    name="Teacher"
                                    inputRef={teacherRef}
                                    label="Teacher"
                                    error={!!errors.teacher}
                                >
                                    {initialData.enseignantsList?.map((ens) => (
                                        <MenuItem key={ens.idPersonnel} value={ens.idPersonnel}>
                                            {`${ens.nom} ${ens.prenom}`}
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.teacher && <Typography color="error">{errors.teacher}</Typography>}
                            </FormControl>
                        </Grid>
                        <Grid item xs={6}>
                            <FormControl fullWidth margin="dense">
                                <InputLabel>Level</InputLabel>
                                <Select
                                    name="Level"
                                    inputRef={levelRef}
                                    label="Level"
                                    error={!!errors.level}
                                >
                                    {initialData.niveauList?.map((niv) => (
                                        <MenuItem key={niv.idNiv} value={niv.idNiv}>
                                            {niv.type}
                                        </MenuItem>
                                    ))}
                                </Select>
                                {errors.level && <Typography color="error">{errors.level}</Typography>}
                            </FormControl>
                        </Grid>

                        <Box sx={{ display: 'flex', justifyContent: 'flex-end', marginTop: '20px' }}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={handleClickOpen}
                                sx={{  marginLeft: '70rem',marginTop: "10px" }}
                            >
                                Submit
                            </Button>
                        </Box>
                    </Grid>
                </Paper>

                <DialogAndSnackBar
                    handleConfirmAction={handleSubmit}
                    actionType="Submit"
                    open={open}
                    setOpen={setOpen}
                    snackbarOpen={snackbarOpen}
                    setSnackbarOpen={setSnackbarOpen}
                    snackbarSeverity={snackbarSeverity}
                    snackbarMessage={snackbarMessage}
                />
            </Container>
        </>
    );
}
