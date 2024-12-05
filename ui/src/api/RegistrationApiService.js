import {apiClient} from './ApiClient';

export const executeUserRegistration
    = (firstName, lastName,email, password) =>
    apiClient.post(`/register`,{firstName, lastName,email, password})