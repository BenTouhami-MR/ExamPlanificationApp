import React, { useEffect, useState } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Button,
    Grid,
    MenuItem,
    Select,
    Paper,
    Typography,
    CircularProgress,
    Alert,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { keyframes } from '@mui/system';
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from "@mui/material/IconButton";
import EditIcon from '@mui/icons-material/Edit';
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";


function EducationalElementTable() {
    const [educationalElements, setEducationalElements] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [filter, setFilter] = useState("All");
    const [filteredEducationalElements, setFilteredEducationalElements] = useState([]);
    const [open, setOpen] = useState(false);
    const [actionType, setActionType] = useState();
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [currentElement, setCurrentElement] = useState();
    const navigate = useNavigate();


    const handleClickOpen = (action, element) => {
        setActionType(action);
        setOpen(true);
        setCurrentElement(element);
        console.log(element);
    }

    const handleConfirmAction = (element) => {
        if (actionType === 'delete') {
            handleDeletion(element);
        } else {
            handleUpdate(element);
        }
    }

    const handleUpdate = async (element) => {
        console.log(`id after before ======>${element.idElmPedg}`)

        navigate("updateForm", {state: {elmPedagId: element.idElmPedg,element:element}});
        console.log(`id after update ======>${element.idElmPedg}`)
    };

    const handleDeletion = async (element) => {
        try {
            const response = await fetch(`http://localhost:8081/api/admin/elementsPed/delete/${element.idElmPedg}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                const message = await response.text();
                setSnackbarSeverity("success");
                setSnackbarMessage(message);
                setOpen(false);
                setSnackbarOpen(true);
                getEducationalElements();
            } else {
                setSnackbarSeverity("error");
                setSnackbarMessage(`Failed to delete element ${element.titre}!`);
                setOpen(false);
                setSnackbarOpen(true);
            }
        } catch (error) {
            setSnackbarSeverity("error");
            setSnackbarMessage(`Error occurred during deletion of element ${element.titre}!`);
            setSnackbarOpen(true);
            console.error('Error deleting element:', error);
        }
    }

    const getEducationalElements = () => {
        setError(null);
        fetch('http://localhost:8081/api/admin/elementsPed/elsPedg')
            .then(response => {
                if (!response.ok) {
                    setError('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                setEducationalElements(data);
                setFilteredEducationalElements(data);
                if (data.length===0){
                    setError('No data found');
                }
            })
            .catch(error => {
                setError('There was a problem with the fetch operation: ' + error);
            });
    }

    useEffect(() => {
        setLoading(true);
        getEducationalElements();
        setLoading(false);
    }, []);

    // Filtering logic
    useEffect(() => {
        let filteredElements = educationalElements.filter((element) =>
            filter === "All" ? true : element.type === filter
        );

        setFilteredEducationalElements(filteredElements.length === 0 && filter==="All" ? educationalElements : filteredElements);
    }, [filter, educationalElements]);

    const handleFilterChange = (event) => {
        setFilter(event.target.value);
    };



    return (
        <div>
            <Grid container justifyContent="space-between" alignItems="center" sx={{ mb: 2 }}>
                <Button variant="contained" startIcon={<AddIcon />} onClick={() => navigate("Form")} >
                    Add Element
                </Button>

                <Grid item>
                    <Select
                        value={filter}
                        onChange={handleFilterChange}
                        displayEmpty
                        variant="outlined"
                        sx={{
                            width: '200px',
                            borderRadius: '50px',
                            backgroundColor: '#f0f0f0',
                            '& .MuiOutlinedInput-root': {
                                borderRadius: '50px',
                            },
                            '& .MuiOutlinedInput-input': {
                                padding: '10px 14px',
                            },
                        }}
                    >
                        <MenuItem value="All">All</MenuItem>
                        <MenuItem value="module">Modules</MenuItem>
                        <MenuItem value="element">Elements</MenuItem>
                    </Select>
                </Grid>
            </Grid>

            <Paper elevation={3} sx={{ padding: 8, borderRadius: 7 }}>
                <Typography variant="h6" gutterBottom>
                    Educational Elements List
                </Typography>

                {loading && (
                    <Grid container justifyContent="center" alignItems="center" sx={{ mt: 3, mb: 3 }}>
                        <CircularProgress />
                    </Grid>
                )}

                {error && (
                    <Grid container justifyContent="center" alignItems="center" sx={{ mt: 3, mb: 3 }}>
                        <Alert severity={error === 'No data found' ? 'info' : 'error'}>
                            {error}
                        </Alert>
                    </Grid>
                )}

                {!loading && !error && (
                    <>
                        <Table sx={{ minWidth: 1200, borderCollapse: 'separate', borderSpacing: '0 15px' }}>
                            <TableHead>
                                <TableRow>
                                    <TableCell sx={{ fontWeight: 'bold', color: '#555', borderBottom: 'none' }}>Type</TableCell>
                                    <TableCell sx={{ fontWeight: 'bold', color: '#555', borderBottom: 'none' }}>Title</TableCell>
                                    <TableCell sx={{ fontWeight: 'bold', color: '#555', borderBottom: 'none' }}>Coordinator</TableCell>
                                    <TableCell sx={{ fontWeight: 'bold', color: '#555', borderBottom: 'none' }}>Teacher</TableCell>
                                    <TableCell sx={{ fontWeight: 'bold', color: '#555', borderBottom: 'none' }}>Level</TableCell>
                                    <TableCell sx={{ fontWeight: 'bold', color: '#555', borderBottom: 'none' }}>Actions</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {filteredEducationalElements.map((element, index) => (
                                    <TableRow
                                        key={index}
                                        sx={{
                                            '&:hover': {
                                                backgroundColor: '#f9f5f5',
                                                transform: 'scale(1.001)',
                                                transition: 'transform 1s ease',
                                            },
                                            borderBottom: 'none',
                                            borderRadius: '10px',
                                            overflow: 'hidden',
                                            boxShadow: '0 4px 10px rgba(0, 0, 0, 0.1)',
                                        }}
                                    >
                                        <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                            <span
                                                className={element.type === 'module' ? "badge text-bg-success" : "badge text-bg-info"}>{element.type === 'module' ? 'Module' : 'Element'}</span>
                                        </TableCell>
                                        <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                            {element.titre}
                                        </TableCell>
                                        <TableCell sx={{ padding: 2, fontWeight: "bold" }}>
                                            {element.nom_cordinateur}
                                        </TableCell>
                                        <TableCell sx={{ padding: 2, fontWeight: "bold" }}>
                                            {element.nom_enseignant}
                                        </TableCell>
                                        <TableCell sx={{ padding: 2, fontWeight: "bold" }}>
                                            {element.niveau}
                                        </TableCell>
                                        <TableCell sx={{ padding: 2, fontWeight: "bold" }}>
                                            <IconButton color="error" onClick={() => handleClickOpen("delete", element)}>
                                                <DeleteIcon />
                                            </IconButton>
                                            <IconButton color="primary" onClick={() => handleClickOpen("update", element)}>
                                                <EditIcon />
                                            </IconButton>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>

                        <DialogAndSnackBar
                            handleConfirmAction={() => handleConfirmAction(currentElement)}
                            actionType={actionType}
                            open={open}
                            setOpen={setOpen}
                            snackbarOpen={snackbarOpen}
                            setSnackbarOpen={setSnackbarOpen}
                            snackbarSeverity={snackbarSeverity}
                            snackbarMessage={snackbarMessage}
                        />
                    </>
                )}
            </Paper>
        </div>
    );
};

export default EducationalElementTable;
