/**
 * Array based storage for Resumes
 */
public class ArrayStorage
{
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear()
    {
    }

    void save(Resume resume)
    {
        if (resume == null) return;

        if (resume.uuid.isEmpty()) return;

        for (int i = 0; i < storage.length; i++)
        {
            if (storage[i].uuid.equals(resume.uuid))
            {
                // Перезаписывать или выбрасить Exception???
                storage[i] = resume;
                return;
            }
        }

        if ((size + 1) > storage.length)
        {
            // Что делать при переполнении базы???
            return;
        }

        storage[size++] = resume;
    }

    Resume get(String uuid)
    {
        return null;
    }

    void delete(String uuid)
    {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll()
    {
        return new Resume[0];
    }

    int size()
    {
        return 0;
    }
}
