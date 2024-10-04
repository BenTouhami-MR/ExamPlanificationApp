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
import { useNavigate} from 'react-router-dom';
import { keyframes } from '@mui/system';
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from "@mui/material/IconButton";
import EditIcon from '@mui/icons-material/Edit';
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";



function PersonnelTable(){
    const [personnels ,setPersonnels]  = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [filter, setFilter] =useState("3")
    const [filterdPersonnels,setFilterdPersonnels] = useState([])
    const [open,setOpen] =useState(false)
    const [actionType,setActionType] = useState()
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [currentPersonnel,setCurrentPersonnel] =useState();
    const navigate  =useNavigate();



    const handleClickOpen = (action,personnel) => {

        setActionType(action);
        setOpen(true);
        setCurrentPersonnel(personnel);
        console.log(personnel);
    }



    const handleConfirmAction= (personnel)=>{
        //TODO
        console.log(actionType);

        if (actionType==='delete'){

            handleDeletion(personnel)

        }else{
            handleUpdate(personnel)
        }

    }




    /**
     *
     * @param idPersonnel
     * @returns {Promise<void>}
     */
    const handleUpdate = async (personnel) => {
        navigate("UpdatePersonnel", {state: {personnelId: personnel.idPersonnel,p:personnel}});
    };

    /**
     *
     * @param personnel
     * @returns {Promise<void>}
     */
    const handleDeletion = async (personnel) => {
            try {
                const response = await fetch(`http://localhost:8081/api/admin/personnel/${personnel.idPersonnel}`, {
                    method: 'DELETE',
                });
                if (response.ok) {
                    // Handle success (e.g., refresh data or show a success message)
                    const message = await response.text();
                    setSnackbarSeverity("success")
                    setSnackbarMessage(message);
                    setOpen(false);
                    setSnackbarOpen(true);
                    getPersonnels()

                } else {
                    setSnackbarSeverity("error")
                    setSnackbarMessage(`Failed to delete Personnel  ${personnel.nom} ${personnel.prenom} because this teacher has a module to teach!`);
                    setOpen(false);
                    setSnackbarOpen(true);
                }
            } catch (error) {
                // Handle error
                setSnackbarSeverity("error")
                setSnackbarMessage(`Error occurred during deletion of Personnel ${personnel.nom} ${personnel.prenom}!`);
                setSnackbarOpen(true);
                console.error('Error deleting personnel:', error);
            }

        }


    const getPersonnels =  () =>{
        setError(null)
        fetch('http://localhost:8081/api/admin/personnels')
            .then(response => {
                if (!response.ok) {
                    setError('Network response was not ok ' + response.statusText+" : There was a problem with the fetch operation");
                }
                return response.json(); // Parse the JSON data from the response
            })
            .then(data => {
                console.log(data);
                setPersonnels(data)// Handle the data fetched
                setFilterdPersonnels(data)
                setLoading(false)
            })
            .catch(error => {
                setError('There was a problem with the fetch operation:', error);
                setLoading(false)

            });
    }


        useEffect(() => {
            getPersonnels()

        }, []);

        // Filtering logic
        useEffect(() => {
            let filteredPers = personnels.filter((p) =>
                p.typePersonnel.toString() === filter
            );


            if (filteredPers.length === 0) {
                setFilterdPersonnels(personnels)
            } else {
                setFilterdPersonnels(filteredPers);
            }


        }, [filter]);



        const handleFilterChange = (event) => {
            setFilter(event.target.value);
        };


        const glow = keyframes`
            0% {
                box-shadow: 0 0 5px #ff9800, 0 0 10px #ff9800, 0 0 20px #ff9800;
            }
            50% {
                box-shadow: 0 0 20px #ff9800, 0 0 30px #ff9800, 0 0 40px #ff9800;
            }
            100% {
                box-shadow: 0 0 5px #ff9800, 0 0 10px #ff9800, 0 0 20px #ff9800;
            }
        `;

        return (
            <div>
                <Grid container justifyContent="space-between" alignItems="center" sx={{mb: 2}}>
                        <Button variant="contained" startIcon={<AddIcon/>} onClick={()=>navigate("Form")} >
                            Add Personnel
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
                            <MenuItem value="3">All</MenuItem>
                            <MenuItem value="2">Teachers</MenuItem>
                            <MenuItem value="1">Administrators</MenuItem>
                        </Select>
                    </Grid>
                </Grid>


                <Paper elevation={3} sx={{padding: 8, borderRadius: 7}}>
                    <Typography variant="h6" gutterBottom>
                        personnels List
                    </Typography>

                    {loading && (
                        <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                            <CircularProgress/>
                        </Grid>
                    )}

                    {error && (
                        <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                            <Alert severity={error === 'No documents found' ? 'info' : 'error'}>
                                {error}
                            </Alert>
                        </Grid>
                    )}

                    {!loading && !error && (
                        <>
                            <Table sx={{minWidth: 1100, borderCollapse: 'separate', borderSpacing: '0 15px'}}>
                                <TableHead>
                                    <TableRow>
                                        <TableCell sx={{
                                            fontWeight: 'bold',
                                            color: '#555',
                                            borderBottom: 'none'
                                        }}>Type</TableCell>
                                        <TableCell sx={{fontWeight: 'bold', color: '#555', borderBottom: 'none'}}>First
                                            Name</TableCell>
                                        <TableCell sx={{
                                            fontWeight: 'bold',
                                            color: '#555',
                                            borderBottom: 'none'
                                        }}>Name</TableCell>
                                        <TableCell sx={{
                                            fontWeight: 'bold',
                                            color: '#555',
                                            borderBottom: 'none'
                                        }}>Sector</TableCell>
                                        <TableCell sx={{
                                            fontWeight: 'bold',
                                            color: '#555',
                                            borderBottom: 'none'
                                        }}>Department</TableCell>
                                        <TableCell sx={{
                                            fontWeight: 'bold',
                                            color: '#555',
                                            borderBottom: 'none'
                                        }}>Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {filterdPersonnels
                                        .map((personnel, index) => (
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
                                                        className={personnel.typePersonnel === 2 ? "badge text-bg-success" : "badge text-bg-info"}>{personnel.typePersonnel === 2 ? 'Teacher' : 'Administrator'}</span>
                                                </TableCell>

                                                <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                    {personnel.nom}
                                                </TableCell>

                                                <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                    {personnel.prenom}
                                                </TableCell>

                                                <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                    {personnel.typePersonnel === 2 ? personnel.nom_filiere : '---'}
                                                </TableCell>

                                                <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                    {personnel.typePersonnel === 2 ? personnel.nom_departement : '---'}
                                                </TableCell>

                                                <TableCell sx={{padding: 2, fontWeight: "bold"}}>

                                                    <IconButton color="error" onClick={()=>handleClickOpen("delete",personnel)}>
                                                        <DeleteIcon/>
                                                    </IconButton>
                                                    <IconButton color="primary" onClick={()=>handleClickOpen("update",personnel)}>
                                                        <EditIcon/>
                                                    </IconButton>
                                                </TableCell>

                                                {/*<TableCell sx={{ padding: 2 }}>*/}
                                                {/*    <CheckCircle sx={{ color: 'green' }} />*/}
                                                {/*</TableCell>*/}

                                                {/*<TableCell sx={{ padding: 2 }}>*/}
                                                {/*    <Button*/}
                                                {/*        // onClick={() => handleValidateClick(doc)}*/}
                                                {/*        variant="contained"*/}
                                                {/*        sx={{*/}
                                                {/*            backgroundColor: '#ff9800',*/}
                                                {/*            color: '#fff',*/}
                                                {/*            borderRadius: '30px',*/}
                                                {/*            padding: '8px 20px',*/}
                                                {/*            textTransform: 'none',*/}
                                                {/*            boxShadow: '0px 3px 6px rgba(0, 0, 0, 0.1)',*/}
                                                {/*            animation:`${glow} 2s infinite alternate`,*/}
                                                {/*        }}*/}
                                                {/*    >*/}
                                                {/*        Validate*/}
                                                {/*    </Button>*/}
                                                {/*</TableCell>*/}
                                            </TableRow>
                                        ))}
                                </TableBody>
                            </Table>

                            <DialogAndSnackBar handleConfirmAction={()=>handleConfirmAction(currentPersonnel)}
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
export default PersonnelTable;