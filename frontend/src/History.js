import React from "react"
import './history.css'
import { useTable } from 'react-table'

function Table({ columns, data }) {
    // Use the state and functions returned from useTable to build your UI
    const {
      getTableProps,
      getTableBodyProps,
      headerGroups,
      rows,
      prepareRow,
    } = useTable({
      columns,
      data,
    })
  
    // Render the UI for your table
    return (
      <table {...getTableProps()}>
        <thead>
          {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps()}>{column.render('Header')}</th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.map((row, i) => {
            prepareRow(row)
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => {
                  return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                })}
              </tr>
            )
          })}
        </tbody>
      </table>
    )
  }
  

const History = ({data}) => {

    const columns = React.useMemo(
        () => [
          {
            Header: 'Data zmiany',
            accessor: 'createdDate'
          },
          {
            Header: 'Virtua id',
            accessor: 'idVirtua'
          },
          {
            Header: 'Sygnatura',
            accessor: 'signature'
          },
          {
            Header: 'Kod kreskowy',
            accessor: 'barcode'
          },
          {
            Header: 'Autor',
            accessor: 'author'
          },
          {
            Header: 'Tytół',
            accessor: 'title'
          },
          {
            Header: 'Status',
            accessor: 'status'
          },
        ],
        []
      )

    return(
        <Table columns={columns} data={data} />
    );
}

export default History