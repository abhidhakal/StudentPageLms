import {useNavigate} from "react-router-dom";
import {useQuery} from "@tanstack/react-query";
import axios from "axios";
import StudentForm from "./StudentForm.tsx";
import { Key, ReactElement, JSXElementConstructor, ReactNode, ReactPortal } from "react";


function Attendance() {

    const navigate = useNavigate();

    const getData = useQuery({
        queryKey: ["GET_CLASS_DATA"],
        queryFn() {
            return axios.get("http://localhost:8080/class/get")
        },
        select(data) {
            return data?.data?.data
        }

    })

    console.log(getData?.data)

    return (
        <>


            <button onClick={() => navigate("/students")}>click here to add new user.</button>
            <table border={1}>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>student name</th>
                </tr>
                </thead>
                <tbody>
                {getData?.data?.map((i: { id: Key | null | undefined; className: string | number | boolean | ReactElement<any, string | JSXElementConstructor<any>> | Iterable<ReactNode> | ReactPortal | null | undefined; fees: string | number | boolean | ReactElement<any, string | JSXElementConstructor<any>> | Iterable<ReactNode> | ReactPortal | null | undefined; })=>(
                    <tr key={i?.id}>
                        <td>
                            {i?.className}
                        </td>
                        <td>
                            {i?.fees}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <StudentForm/>
        </>
    )
}

export default Attendance ;