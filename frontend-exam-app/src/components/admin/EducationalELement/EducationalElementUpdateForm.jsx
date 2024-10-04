import React, { useState, useEffect, useRef } from "react";
import { Box, Button, Container, FormControl, Grid, InputLabel, MenuItem, Paper, Select, TextField, Typography } from "@mui/material";
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";
import { useLocation, useNavigate } from "react-router-dom";

export default function EducationalElementUpdateForm() {
    const [open, setOpen] = useState(false);
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [errors, setErrors] = useState({});
    const [initialData, setInitialData] = useState({}); // For lists like typeElementList, enseignantsList, and niveauList
    const [actionType, setActionType] = useState();
    const navigate = useNavigate();

    const location = useLocation();
    const { elmPedagId, element } = location.state; // Retrieve state from navigation
    console.log(`Received Id =====> ${elmPedagId}`)
    const [educationalElement, setEducationalElement] = useState({
        idElmPedg: elmPedagId,
        titre: element.titre,
        idTypeElm: element.idTypeElm,
        idCordinateur: element.idCordinateur,
        idEnseignant: element.idEnseignant,
        idNiveau: element.idNiveau
    }); // Store fetched element data

    // UseRef hooks for form fields
    const titleRef = useRef('');
    const typeElementRef = useRef('');
    const coordinatorRef = useRef('');
    const teacherRef = useRef('');
    const levelRef = useRef('');

    // Fetch initial data for dropdowns like typeElementList, enseignantsList, niveauList
    const getInitialData = async () => {
        try {
            const response = await fetch(`http://localhost:8081/api/admin/elementsPed/initialData`);
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
        getInitialData(); // Fetch initial data on component load
    }, []);

    // Populate refs with existing educational element data
    useEffect(() => {
        if (educationalElement) {
            titleRef.current.value = educationalElement.titre || '';
            typeElementRef.current.value = educationalElement.idTypeElm || '';
            coordinatorRef.current.value = educationalElement.idCordinateur || '';
            teacherRef.current.value = educationalElement.idEnseignant || '';
            levelRef.current.value = educationalElement.idNiveau || '';
        }
    }, [educationalElement]);

    // Handle opening dialog
    const handleOpenDialog = (action) => {
        setActionType(action);
        setOpen(true);
    };

    // Handle confirmation
    const handleConfirmation = () => {
        if (actionType === 'update') {
            setOpen(false);
            handleUpdate();
        } else {
            navigate('/EducationalElement');
        }
    };

    // Form validation
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

    // Send updated element to server
    const sendElementToBeUpdated = async (elementData) => {


        console.log(elmPedagId)
        try {
            const response = await fetch(`http://localhost:8081/api/admin/elementsPed/update/${elmPedagId}`, {
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
                setSnackbarMessage("Failed to update educational element");
                setSnackbarSeverity("error");
                setSnackbarOpen(true);
            }
        } catch (error) {
            setSnackbarMessage("An error occurred while updating educational element");
            setSnackbarSeverity("error");
            setSnackbarOpen(true);
        }
    };

    // Handle form submission
    const handleUpdate = () => {
        if (formValidated()) {
            let elementData = {
                idElmPedg: elmPedagId,
                titre: titleRef.current.value,
                idTypeElm: typeElementRef.current.value,
                idCordinateur: coordinatorRef.current.value,
                idEnseignant: teacherRef.current.value,
                idNiveau: levelRef.current.value
            };

            console.log("Updated Element Data:", elementData);
            sendElementToBeUpdated(elementData);
            setEducationalElement(elementData); // Update local state
            setOpen(false);
        }
    };

    return (
        <>
            <Box height={30} />
            <Container sx={{ display: "flex", justifyContent: "center", alignItems: "center", marginTop: "60px" }}>
                <Paper sx={{ padding: '60px' }}>
                    <Typography variant="h5" gutterBottom>
                        Update Educational Element
                    </Typography>
                    <Grid container spacing={6}>
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                label="Title"
                                name="Title"
                                inputRef={titleRef}
                                defaultValue={educationalElement?.titre}
                                error={!!errors.title}
                                variant="outlined"
                                margin="dense"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <FormControl fullWidth margin="dense">
                                <InputLabel>Element Type</InputLabel>
                                <Select
                                    label="Element Type"
                                    name="ElementType"
                                    inputRef={typeElementRef}
                                    defaultValue={educationalElement?.idTypeElm || ''}

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
                                    label ="Coordinator"
                                    inputRef={coordinatorRef}
                                    defaultValue={educationalElement?.idCordinateur || ''}
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
                                    label ="Teacher"
                                    inputRef={teacherRef}
                                    defaultValue={educationalElement?.idEnseignant || ''}
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
                                    label ="Level"
                                    inputRef={levelRef}
                                    defaultValue={educationalElement?.idNiveau || ''}
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
                                color="secondary"
                                onClick={() => handleOpenDialog("cancel")}
                                sx={{ marginLeft: '1rem', marginTop: "10px" }}
                            >
                                Cancel
                            </Button>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => handleOpenDialog("update")}
                                sx={{ marginLeft: '70rem', marginTop: "10px" }}
                            >
                                Update
                            </Button>
                        </Box>
                    </Grid>
                </Paper>
                <DialogAndSnackBar
                    handleConfirmAction={handleConfirmation}
                    actionType="Update"
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
