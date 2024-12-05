import { apiClient } from './ApiClient'

export const getBooksApi
    = (pageNumber, pageSize) =>
    apiClient.post(`/books/list`,{pageNumber, pageSize})

